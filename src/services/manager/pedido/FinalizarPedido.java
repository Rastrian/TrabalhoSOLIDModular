package services.manager.pedido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.PedidosDao;
import profiles.Pedido;
import services.utils.ObserverUtils;

public class FinalizarPedido implements Runnable{
	private volatile boolean closeThread;
    
    private static PedidosDao repository;
	private static ObserverUtils utilsOB;
	
	@Override
	public void run() {
		while (!closeThread) {
            try {
                utilsOB = ObserverUtils.getInstance();
				repository = PedidosDao.getInstance();
			} catch (IOException e) {
				e.printStackTrace();
			}
            this.start();
        }		
	}
	
	public void start() {
		Thread t = null;
		ListarPedidos listarPedido = new ListarPedidos();
    	t = new Thread(listarPedido);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nDigite o código do pedido");
        Integer output = null;
        while(output == null) {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                output = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Insira um formato valido");
            }
        }
        Pedido pedido = repository.get(output); 
        if(pedido != null && pedido.getState() == 1) {
            repository.remove(pedido);
            utilsOB.setState(2, pedido);
            repository.add(pedido);
        }else {
        	System.out.println("Apenas é possível finalizar um pedido PREPARANDO");
        }
        shutdown();        
	}
	
	public void shutdown() {
        closeThread = true;
    }

}
