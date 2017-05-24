package particles;

import java.util.ArrayList;
import java.util.List;


public class ParticleSystemInstant<P extends Particle> extends ParticleSystem<P> {

	@Override
	public List<P> generate(double timeFrom, double timeTo) {
		setFinished(true);
		return new ArrayList<P>();
	}
	
}
