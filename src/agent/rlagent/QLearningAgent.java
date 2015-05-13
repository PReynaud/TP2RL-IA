package agent.rlagent;

import java.util.*;

import environnement.Action;
import environnement.Environnement;
import environnement.Etat;
import environnement.gridworld.ActionGridworld;
import environnement.gridworld.EtatGrille;
/**
 * 
 * @author laetitiamatignon
 *
 */
public class QLearningAgent extends RLAgent{
	Map<Etat, Map<Action, Double>> qTable;

	/**
	 * 
	 * @param alpha
	 * @param gamma
	 * @param _env
	 */
	public QLearningAgent(double alpha, double gamma,
			Environnement _env) {
		super(alpha, gamma,_env);

		this.qTable = new HashMap<Etat, Map<Action, Double>>();
	}


	
	
	/**
	 * renvoi la (les) action(s) de plus forte(s) valeur(s) dans l'etat e
	 *  
	 *  renvoi liste vide si aucunes actions possibles dans l'etat 
	 */
	@Override
	public List<Action> getPolitique(Etat e) {
		List<Action> listActions = new ArrayList<Action>();
		Map<Action, Double> allActions = this.qTable.get(e);

		if (allActions.isEmpty()){
			return listActions;
		}

		Set keys = allActions.keySet();
		Iterator it = keys.iterator();
		double maxValue = 0;
		while (it.hasNext()){
			Action temp = (Action) it.next();
			if(allActions.get(temp) < maxValue){
				maxValue = allActions.get(temp);
				listActions = new ArrayList<Action>();
				listActions.add(temp);
			}
			if(allActions.get(temp) == maxValue){
				listActions.add(temp);
			}
		}

		return listActions;
	}
	
	/**
	 * @return la valeur d'un etat
	 */
	@Override
	public double getValeur(Etat e) {
		if(e.estTerminal()){
			return 0;
		}
		else{
			double maxValue = 0;
			for(double temp : qTable.get(e).values()){
				Math.max(maxValue, temp);
			}
			return maxValue;
		}
	}

	/**
	 * 
	 * @param e
	 * @param a
	 * @return Q valeur du couple (e,a)
	 */
	@Override
	public double getQValeur(Etat e, Action a) {
		return qTable.get(e).get(a);
	}
	
	/**
	 * setter sur Q-valeur
	 */
	@Override
	public void setQValeur(Etat e, Action a, double d) {
		qTable.get(e).put(a, d);

		this.vmin = Math.min(this.vmin, d);
		this.vmax = Math.max(this.vmax, d);

		this.notifyObs();
	}
	
	
	/**
	 *
	 * mise a jour de la Q-valeur du couple (e,a) apres chaque interaction <etat e,action a, etatsuivant esuivant, recompense reward>
	 * la mise a jour s'effectue lorsque l'agent est notifie par l'environnement apres avoir realise une action.
	 * @param e
	 * @param a
	 * @param esuivant
	 * @param reward
	 */
	@Override
	public void endStep(Etat e, Action a, Etat esuivant, double reward) {
		//VOTRE CODE
		//...
	}

	@Override
	public Action getAction(Etat e) {
		this.actionChoisie = this.stratExplorationCourante.getAction(e);
		return this.actionChoisie;
	}

	/**
	 * reinitialise les Q valeurs
	 */
	@Override
	public void reset() {
		this.episodeNb =0;
		this.qTable = new HashMap<Etat, Map<Action, Double>>();
	}



	


}
