package control;

import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.AltingChannelOutputInt;
import org.jcsp.lang.CSProcess;

public class Caixa implements CSProcess {

	private int id;
	private int fat;
	
	private final AltingChannelInputInt pagamento_chan_x_in;
	private final AltingChannelInputInt pagamento_chan_i_in;
	private final AltingChannelInputInt pagamento_chan_y_in;
	
	private final AltingChannelOutputInt pagamento_chan_x_out;
	private final AltingChannelOutputInt pagamento_chan_i_out;
	private final AltingChannelOutputInt pagamento_chan_y_out;

	public Caixa(int id, int fat, AltingChannelInputInt chanIn[], AltingChannelOutputInt chanOut[]) {
		this.id = id;
		this.fat = fat;
		this.pagamento_chan_x_in = chanIn[0];
		this.pagamento_chan_i_in = chanIn[1];
		this.pagamento_chan_y_in = chanIn[2];
		this.pagamento_chan_x_out = chanOut[0];
		this.pagamento_chan_i_out = chanOut[1];
		this.pagamento_chan_y_out = chanOut[2];
	}
	
	@Override
	public void run() {
		while(true) {
			pagamento_chan_x_in.read();
			pagamento_chan_i_out.write(id);
			pagamento_chan_y_in.read();
		}
	}

}
