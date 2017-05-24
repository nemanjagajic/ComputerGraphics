package particles;

import mars.utils.Numeric;

import java.util.ArrayList;
import java.util.List;


public abstract class ParticleSystemPoisson<P extends Particle> extends ParticleSystem<P> {
	double meanInterval;
	
	private double tNext;

	
	
	public ParticleSystemPoisson(double meanInterval, double timeStart) {
		this.meanInterval = meanInterval;
		this.tNext = timeStart;		
	}
	
	
	protected abstract P generateParticle(double time);
	
	
	
	
	@Override
	public List<P> generate(double timeFrom, double timeTo) {
		List<P> particles = new ArrayList<P>();
		
		while (tNext < timeTo) {
			P particle = generateParticle(tNext);
			particles.add(particle);
			
			tNext += Numeric.randomExponential(meanInterval);
		}
		
		return particles;
	}

}
