package control;

import org.jcsp.lang.*;

public class Cabeleireiro implements CSProcess {

	private int id;
	private final AltingChannelInputInt corte_chan_x_in;
	private final AltingChannelInputInt penteado_chan_x_in;
	private final AltingChannelInputInt lavagemC_chan_x_in;
	private final AltingChannelInputInt lavagemCP_chan_x_in;
	
	private final AltingChannelOutputInt corte_chan_x_out;
	private final AltingChannelOutputInt penteado_chan_x_out;
	private final AltingChannelOutputInt lavagemC_chan_x_out;
	private final AltingChannelOutputInt lavagemCP_chan_x_out;
	
	private final AltingChannelInputInt corte_chan_i_in;
	private final AltingChannelInputInt penteado_chan_i_in;
	private final AltingChannelInputInt lavagemC_chan_i_in;
	private final AltingChannelInputInt lavagemCP_chan_i_in;
	
	private final AltingChannelOutputInt corte_chan_i_out;
	private final AltingChannelOutputInt penteado_chan_i_out;
	private final AltingChannelOutputInt lavagemC_chan_i_out;
	private final AltingChannelOutputInt lavagemCP_chan_i_out;
	
	public Cabeleireiro(int id, AltingChannelInputInt chanIn[], AltingChannelOutputInt chanOut[]) {
		this.id = id;
		this.corte_chan_x_in = chanIn[0];
		this.penteado_chan_x_in = chanIn[1];
		this.lavagemC_chan_x_in = chanIn[2];
		this.lavagemCP_chan_x_in = chanIn[3];
		
		this.corte_chan_x_out = chanOut[0];
		this.penteado_chan_x_out = chanOut[1];
		this.lavagemC_chan_x_out = chanOut[2];
		this.lavagemCP_chan_x_out = chanOut[3];
		
		this.corte_chan_i_in = chanIn[4];
		this.penteado_chan_i_in = chanIn[5];
		this.lavagemC_chan_i_in = chanIn[6];
		this.lavagemCP_chan_i_in = chanIn[7];
		
		this.corte_chan_i_out = chanOut[4];
		this.penteado_chan_i_out = chanOut[5];
		this.lavagemC_chan_i_out = chanOut[6];
		this.lavagemCP_chan_i_out = chanOut[7];
	}
	
	public void run() {
		final Guard[] guards = new Guard[]{lavagemC_chan_x_in, lavagemCP_chan_x_in, penteado_chan_x_in};
		Alternative alt = new Alternative(guards);
		
		final int C_LAVC = 0;
		final int C_PEN = 1;
		final int C_LAVCP = 2;
		
		switch(alt.select()) {
		case C_LAVC:
			lavagemC_chan_x_in.read();
			lavagemC_chan_i_out.write(id);
			
			corte_chan_x_out.write(lavagemC_chan_x_in.read());
			corte_chan_i_out.write(id);
		
		case C_PEN:
			penteado_chan_x_in.read();
			penteado_chan_i_out.write(id);
			
		case C_LAVCP:
			lavagemC_chan_x_in.read();
			lavagemC_chan_i_out.write(id);
			
			corte_chan_x_out.write(lavagemC_chan_x_in.read());
			corte_chan_i_out.write(id);
			
			penteado_chan_x_out.write(lavagemC_chan_x_in.read());
			penteado_chan_i_out.write(id);
		}
	}

}
