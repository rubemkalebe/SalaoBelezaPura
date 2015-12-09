package control;

import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.AltingChannelOutputInt;
import org.jcsp.lang.CSProcess;

public class Depiladora implements CSProcess {

	private int id;
	private int fat;
	
	private final AltingChannelInputInt depiladora_chan_x_in;
	private final AltingChannelInputInt depiladora_chan_i_in;
	private final AltingChannelInputInt fat_depiladora_chan_fat_in;
	
	private final AltingChannelOutputInt depiladora_chan_x_out;
	private final AltingChannelOutputInt depiladora_chan_i_out;
	private final AltingChannelOutputInt fat_depiladora_chan_fat_out;

	public Depiladora(int id, int fat, AltingChannelInputInt chanIn[], AltingChannelOutputInt chanOut[]) {
		this.id = id;
		this.fat = fat;
		this.depiladora_chan_x_in = chanIn[0];
		this.depiladora_chan_i_in = chanIn[1];
		this.fat_depiladora_chan_fat_in = chanIn[2];
		this.depiladora_chan_x_out = chanOut[0];
		this.depiladora_chan_i_out = chanOut[1];
		this.fat_depiladora_chan_fat_out = chanOut[2];
	}
	
	@Override
	public void run() {
		while(true) {
			depiladora_chan_x_in.read();
			depiladora_chan_i_out.write(id);
			
			fat_depiladora_chan_fat_out.write(depiladora_chan_x_in.read());
			fat_depiladora_chan_fat_out.write(id);
			fat_depiladora_chan_fat_out.write(fat+40);
		}
	}

}
