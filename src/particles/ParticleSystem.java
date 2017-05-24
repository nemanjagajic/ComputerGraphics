package particles;

import mars.drawingx.drawing.View;

import java.util.ArrayList;
import java.util.List;


public abstract class ParticleSystem<P extends Particle> {

	public abstract List<P> generate(double timeFrom, double timeTo);
	
	protected ArrayList<P> particles = new ArrayList<P>();
	
	private double timeLastUpdate = Double.NEGATIVE_INFINITY;
	
	private boolean finished = false;
	
	
	
	protected void clean(double time) {
		int n = particles.size();
		int i = 0;
		while (i < n) {
			P particle = particles.get(i);
			if (!particle.isAlive(time)) {
				n--;
				particles.set(i, particles.get(n));
			} else {
				i++;
			}
		}
		
		for (int j = particles.size(); j > n; j--) {
			particles.remove(j - 1);
		}
	}
	
	
	public void update(double time) {
		if (time > timeLastUpdate) {
			if (!finished) {
				particles.addAll(generate(timeLastUpdate, time));
			}
			timeLastUpdate = time;
			
			clean(time);
		}
	}
	
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	
	public void draw(View view, double time) {
		update(time);
		for (Particle particle : particles) {
			particle.draw(view, time);
		}		
	}
	
	
	public boolean isAlive(double time) {
		return !(finished && particles.isEmpty());
	}

}
