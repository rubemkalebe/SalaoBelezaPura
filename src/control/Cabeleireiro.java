package control;

import org.jcsp.lang.*;

public class Cabeleireiro implements CSProcess {

	private int id;
	private int fat;
	private final AltingChannelInputInt corte_chan_x_in;
	private final AltingChannelInputInt penteado_chan_x_in;
	private final AltingChannelInputInt lavagemC_chan_x_in;
	private final AltingChannelInputInt lavagemCP_chan_x_in;
	private final AltingChannelInputInt fat_cabelereira_chan_x_in;
	private final AltingChannelInputInt fat_cabelereira_chan_fat_in;
	
	private final AltingChannelOutputInt corte_chan_x_out;
	private final AltingChannelOutputInt penteado_chan_x_out;
	private final AltingChannelOutputInt lavagemC_chan_x_out;
	private final AltingChannelOutputInt lavagemCP_chan_x_out;
	private final AltingChannelOutputInt fat_cabelereira_chan_x_out;
	private final AltingChannelOutputInt fat_cabelereira_chan_fat_out;
	
	private final AltingChannelInputInt corte_chan_i_in;
	private final AltingChannelInputInt penteado_chan_i_in;
	private final AltingChannelInputInt lavagemC_chan_i_in;
	private final AltingChannelInputInt lavagemCP_chan_i_in;
	private final AltingChannelInputInt fat_cabelereira_chan_i_in;
	
	private final AltingChannelOutputInt corte_chan_i_out;
	private final AltingChannelOutputInt penteado_chan_i_out;
	private final AltingChannelOutputInt lavagemC_chan_i_out;
	private final AltingChannelOutputInt lavagemCP_chan_i_out;
	private final AltingChannelOutputInt fat_cabelereira_chan_i_out;
	
	public Cabeleireiro(int id, int fat, AltingChannelInputInt chanIn[], AltingChannelOutputInt chanOut[]) {
		this.id = id;
		this.fat = fat;
		this.corte_chan_x_in = chanIn[0];
		this.penteado_chan_x_in = chanIn[1];
		this.lavagemC_chan_x_in = chanIn[2];
		this.lavagemCP_chan_x_in = chanIn[3];
		this.fat_cabelereira_chan_x_in = chanIn[4];
		this.fat_cabelereira_chan_fat_in = chanIn[5];
		
		this.corte_chan_x_out = chanOut[0];
		this.penteado_chan_x_out = chanOut[1];
		this.lavagemC_chan_x_out = chanOut[2];
		this.lavagemCP_chan_x_out = chanOut[3];
		this.fat_cabelereira_chan_x_out = chanOut[4];
		this.fat_cabelereira_chan_fat_out = chanOut[5];
		
		this.corte_chan_i_in = chanIn[6];
		this.penteado_chan_i_in = chanIn[7];
		this.lavagemC_chan_i_in = chanIn[8];
		this.lavagemCP_chan_i_in = chanIn[9];
		this.fat_cabelereira_chan_i_in = chanIn[10];
		
		this.corte_chan_i_out = chanOut[6];
		this.penteado_chan_i_out = chanOut[7];
		this.lavagemC_chan_i_out = chanOut[8];
		this.lavagemCP_chan_i_out = chanOut[9];
		this.fat_cabelereira_chan_i_out = chanOut[10];
	}
	
	public void run() {
		final Guard[] guards = new Guard[]{lavagemC_chan_x_in, lavagemCP_chan_x_in, penteado_chan_x_in};
		Alternative alt = new Alternative(guards);
		
		final int C_LAVC = 0;
		final int C_PEN = 1;
		final int C_LAVCP = 2;
		
		while(true) {
			switch(alt.select()) {
			case C_LAVC:
				lavagemC_chan_x_in.read();
				lavagemC_chan_i_out.write(id);
				
				corte_chan_x_out.write(lavagemC_chan_x_in.read());
				corte_chan_i_out.write(id);
				
				fat_cabelereira_chan_x_out.write(lavagemC_chan_x_in.read());
				fat_cabelereira_chan_i_out.write(id);
				fat_cabelereira_chan_fat_out.write(fat+30);
			
			case C_PEN:
				penteado_chan_x_in.read();
				penteado_chan_i_out.write(id);
				
				fat_cabelereira_chan_x_out.write(penteado_chan_x_in.read());
				fat_cabelereira_chan_i_out.write(id);
				fat_cabelereira_chan_fat_out.write(fat+50);
				
			case C_LAVCP:
				lavagemC_chan_x_in.read();
				lavagemC_chan_i_out.write(id);
				
				corte_chan_x_out.write(lavagemC_chan_x_in.read());
				corte_chan_i_out.write(id);
				
				penteado_chan_x_out.write(lavagemC_chan_x_in.read());
				penteado_chan_i_out.write(id);
				
				fat_cabelereira_chan_x_out.write(lavagemC_chan_x_in.read());
				fat_cabelereira_chan_i_out.write(id);
				fat_cabelereira_chan_fat_out.write(fat+40);
			}	
		}
	}

}
