package MST;

import java.util.Comparator;

public class PresultComparator implements Comparator<Presult> {

	@Override
	public int compare( Presult obj0, Presult obj1) {
		// TODO Auto-generated method stub
		return (obj0.getNumber()-obj1.getNumber());
	}

}
