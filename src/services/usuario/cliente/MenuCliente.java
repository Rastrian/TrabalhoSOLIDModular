package services.usuario.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main_service.MainService;

public class MenuCliente extends MainService{
	private volatile boolean closeThread;
    private static boolean inUse;
	
	@Override
	public void run() {
		while (!closeThread) {
            this.start();
         }
	}
	
	public void start() {
		System.out.println("\nOpções:\n\n0 → Sair.\n1 → Fazer pedido.\n2 → Retirar pedido."
                + "\n\nInsira a opção desejada:");
        Integer output = null;
        while (output == null) {
            if (!inUse) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try {
                    output = Integer.parseInt(br.readLine());
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Insira um formato valido");
                }
            }
        }
        
        if (output == 0) {
            shutdown();
            return;
        }
        if (output != null){
            inUse();
        }
	}
	
	public void inUse(){
        if (inUse){
            inUse = false;
            return;
        }
        inUse = true;
    }
	
	public void shutdown() {
        closeThread = true;
    }
	
}
