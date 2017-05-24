package particles;

import java.util.ArrayList;
import java.util.List;


public abstract class ParticleSystemUniform<P extends Particle> extends ParticleSystem<P> {
	double interval;
	
	private double tNext;

	
	
	public ParticleSystemUniform(double interval, double timeStart) {
		this.interval = interval;
		this.tNext = timeStart;		
	}
	
	
	protected abstract P generateParticle(double time);
	
	
	
	
	@Override
	public List<P> generate(double timeFrom, double timeTo) {
		List<P> particles = new ArrayList<P>();
		
		while (tNext < timeTo) {
			P particle = generateParticle(tNext);
			particles.add(particle);
			
			tNext += interval;
		}
		
		return particles;
	}

}
