package agent.strategy;

import java.util.List;
import java.util.Random;

import agent.rlagent.RLAgent;
import environnement.Action;
import environnement.Etat;

/**
 * Strategie qui renvoit une action aleatoire avec probabilite epsilon, une action gloutonne (qui suit la politique de l'agent) sinon
 * Cette classe a acces a un RLAgent par l'intermediaire de sa classe mere.
 * @author lmatignon
 *
 */
public class StrategyGreedy extends StrategyExploration {
	private double epsilon;
	private Random rand = new Random();

	public StrategyGreedy(RLAgent agent, double epsilon) {
		super(agent);
		this.epsilon = epsilon;
	}

	/**
	 * @return action selectionnee par la strategie d'exploration
	 */
	@Override
	public Action getAction(Etat _e) {
		List<Action> listOfActions = agent.getActionsLegales(_e);
		List<Action> listOfPolitics = agent.getPolitique(_e);

		if (rand.nextDouble() <= epsilon || listOfPolitics.isEmpty()) {
            if(listOfActions.size() == 0){
                return null;
            }
            else
			    return listOfActions.get(rand.nextInt(listOfActions.size()));
		}
		return listOfPolitics.get(rand.nextInt(listOfPolitics.size()));
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

}
