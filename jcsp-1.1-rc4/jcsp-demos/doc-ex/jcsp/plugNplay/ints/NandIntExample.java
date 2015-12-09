import org.jcsp.lang.*;
import org.jcsp.plugNplay.ints.*;

public class NandIntExample {

  public static void main (String[] argv) {

    final One2OneChannelInt a = Channel.one2oneInt ();
    final One2OneChannelInt b = Channel.one2oneInt ();
    final One2OneChannelInt c = Channel.one2oneInt ();
    final One2OneChannelInt d = Channel.one2oneInt ();

    new Parallel (
      new CSProcess[] {
        new NumbersInt (a.out ()),
        new GenerateInt (b.out (), Integer.MAX_VALUE - 1),
        new NandInt (a.in (), b.in (), c.out ()),
        new SuccessorInt (c.in (), d.out ()),
        new PrinterInt (d.in (), "--> ", "\n")
      }
    ).run ();

  }

}
