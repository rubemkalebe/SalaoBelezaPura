package control;

import org.jcsp.lang.*;

public class Cabeleireiro implements CSProcess {

	private int id;
	private final AltingChannelInput corte_chan;
	private final AltingChannelInput lavagemC_chan;
	private final AltingChannelInput lavagemCP_chan;
	
	public Cabeleireiro(int id, AltingChannelInput corte_chan, AltingChannelInput lavagemC_chan,
			AltingChannelInput lavagemCP_chan) {
		this.id = id;
		this.corte_chan = corte_chan;
		this.lavagemC_chan = lavagemC_chan;
		this.lavagemCP_chan = lavagemCP_chan;
	}
	
	public void run() {
		final Guard[] guards = new Guard[]{lavagemC_chan, lavagemCP_chan, corte_chan};
		Alternative alt = new Alternative(guards);
		
		final int C_LAVC = 0;
		final int C_LAVCP = 1;
		final int C_COR = 2;
		
		switch(alt.select()) {
		case C_LAVC:
			ChannelOutput ax;
			AltingChannelInput ay;
			
			
		}
	}

}
