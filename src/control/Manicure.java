package control;

import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.AltingChannelOutputInt;
import org.jcsp.lang.CSProcess;

public class Manicure implements CSProcess {

	private int id;
	private int fat;
	
	private final AltingChannelInputInt manicure_chan_x_in;
	private final AltingChannelInputInt manicure_chan_i_in;
	private final AltingChannelInputInt fat_manicure_chan_fat_in;
	
	private final AltingChannelOutputInt manicure_chan_x_out;
	private final AltingChannelOutputInt manicure_chan_i_out;
	private final AltingChannelOutputInt fat_manicure_chan_fat_out;
	
	public Manicure(int id, int fat, AltingChannelInputInt chanIn[], AltingChannelOutputInt chanOut[]) {
		this.id = id;
		this.fat = fat;
		this.manicure_chan_x_in = chanIn[0];
		this.manicure_chan_i_in = chanIn[1];
		this.fat_manicure_chan_fat_in = chanIn[2];
		this.manicure_chan_x_out = chanOut[0];
		this.manicure_chan_i_out = chanOut[1];
		this.fat_manicure_chan_fat_out = chanOut[2];
	}

	@Override
	public void run() {
		while(true) {
			manicure_chan_x_in.read();
			manicure_chan_i_out.write(id);
			
			fat_manicure_chan_fat_out.write(manicure_chan_x_in.read());
			fat_manicure_chan_fat_out.write(id);
			fat_manicure_chan_fat_out.write(fat+30);
		}
	}

}
