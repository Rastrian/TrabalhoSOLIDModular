package decorator;

public class Croutons extends PedidoDecorator{

	public Croutons(Pedido decoratedPedido) {
		super(decoratedPedido);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return decoratedPedido.getDesc() + " + Croutons";
	}

	@Override
	public double getPreco() {
		// TODO Auto-generated method stub
		return decoratedPedido.getPreco() + 2;
	}

}
