package agent.rlagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private double[] values;


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
		//VOTRE CODE
		//...
		return null;
		
		
	}
	
	/**
	 * @return la valeur d'un etat
	 */
	@Override
	public double getValeur(Etat e) {

		return 0.0;
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
		//VOTRE CODE
		//...
		
	}



	


}
