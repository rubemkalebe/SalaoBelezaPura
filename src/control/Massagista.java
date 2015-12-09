package control;

import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.AltingChannelOutputInt;
import org.jcsp.lang.CSProcess;

public class Massagista implements CSProcess {

	private int id;
	private int fat;
	
	private final AltingChannelInputInt massagem_chan_x_in;
	private final AltingChannelInputInt massagem_chan_i_in;
	private final AltingChannelInputInt fat_massagista_chan_fat_in;
	
	private final AltingChannelOutputInt massagem_chan_x_out;
	private final AltingChannelOutputInt massagem_chan_i_out;
	private final AltingChannelOutputInt fat_massagista_chan_fat_out;

	public Massagista(int id, int fat, AltingChannelInputInt chanIn[], AltingChannelOutputInt chanOut[]) {
		this.id = id;
		this.fat = fat;
		this.massagem_chan_x_in = chanIn[0];
		this.massagem_chan_i_in = chanIn[1];
		this.fat_massagista_chan_fat_in = chanIn[2];
		this.massagem_chan_x_out = chanOut[0];
		this.massagem_chan_i_out = chanOut[1];
		this.fat_massagista_chan_fat_out = chanOut[2];
	}
	
	@Override
	public void run() {
		while(true) {
			massagem_chan_x_in.read();
			massagem_chan_i_out.write(id);
			
			fat_massagista_chan_fat_out.write(massagem_chan_x_in.read());
			fat_massagista_chan_fat_out.write(id);
			fat_massagista_chan_fat_out.write(fat+40);
		}
	}

}
