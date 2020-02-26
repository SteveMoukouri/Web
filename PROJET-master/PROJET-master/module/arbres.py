from soccersimulator import settings,SoccerTeam, Simulation, show_simu, KeyboardStrategy
from soccersimulator import Strategy, SoccerAction, Vector2D, load_jsonz,dump_jsonz,Vector2D
from arbres_utils import build_apprentissage,affiche_arbre,DTreeStrategy,apprend_arbre,genere_dot
from sklearn.tree import export_graphviz
from sklearn.tree import DecisionTreeClassifier
import os.path
from module.Outil import *

## Ma Strategie d'attaque
class MyAttackStrategy(Strategy):
    def __init__(self):
        Strategy.__init__(self,"Ma Strategie d'attaques")
    def compute_strategy(self,state,id_team,id_player):
        my_state = Outil(state, id_team, id_player)
        
        if(my_state.distanceAuBallon()>settings.PLAYER_RADIUS+settings.BALL_RADIUS):
            return my_state.aller(my_state.ball_prediction())    
        return my_state.shoot(my_state.position_but_adv())
        
## Ma Strategie de defense
class MyDefenseStrategy(Strategy):
    def __init__(self):
        Strategy.__init__(self,"Ma Strategie de defenses")
    def compute_strategy(self,state,id_team,id_player):
        my_state = Outil(state, id_team, id_player)
        
        if(my_state.distanceMonBut()<=settings.GAME_WIDTH/3.2):
            if(my_state.distanceAuBallon()>settings.PLAYER_RADIUS+settings.BALL_RADIUS):   
                return my_state.aller(my_state.ball_prediction())
            else:
                return my_state.shoot(my_state.position_but_adv())
        else:
            return my_state.get_position_def()        

## Ma Strategie de dribble
class DribblerStrategy(Strategy):
    def __init__(self):
        Strategy.__init__(self, "Ma strategie de dribbles")
    def compute_strategy(self, state, id_team, id_player):
        my_state = Outil(state, id_team, id_player)
        
        if(my_state.distanceAuBallon()>settings.PLAYER_RADIUS+settings.BALL_RADIUS):
            return my_state.aller(my_state.ball_prediction())
        else:
            if(my_state.distanceAuButAdv()>settings.GAME_WIDTH/3.2):
                return my_state.mini_shoot(my_state.position_but_adv())
            else:
                return my_state.shoot(my_state.position_but_adv())

## Strategie PES                
class IntelligentStrategy(Strategy):
    def __init__(self):
        Strategy.__init__(self, "Strategie Intelligente")
    def compute_strategy(self, state, id_team, id_player):
        my_state = Outil(state, id_team, id_player)
        
        if(my_state.distanceAuButAdv()>settings.GAME_WIDTH/2):
            if(my_state.distanceAuBallon()>settings.PLAYER_RADIUS+settings.BALL_RADIUS):
                return my_state.laisse()
            else:
                return my_state.mini_shoot(my_state.position_but_adv())
        else:    
            if(my_state.distanceAuBallon()>settings.PLAYER_RADIUS+settings.BALL_RADIUS):
                return my_state.laisse()
            else:
                if(my_state.distanceAuButAdv()>settings.GAME_WIDTH/3.2):
                    return my_state.passe() + my_state.trace()    
                else:
                    return my_state.shoot(my_state.position_but_adv())                

#######
## Construction des equipes
#######

team1 = SoccerTeam("EGY")
strat_j1 = KeyboardStrategy()
strat_j1.add('a',MyAttackStrategy())
strat_j1.add('z',DribblerStrategy())
team1.add("Salah",strat_j1)
team1.add("Warda",MyDefenseStrategy())
team2 = SoccerTeam("ALG")
team2.add("Mahrez", IntelligentStrategy())
team2.add("Slimani", IntelligentStrategy())


### Transformation d'un etat en features : state,idt,idp -> R^d
def my_get_features(state,idt,idp):
    """ extraction du vecteur de features d'un etat, ici distance a la balle, distance au but, distance balle but """
    p_pos= state.player_state(idt,idp).position
    f1 = p_pos.distance(state.ball.position)
    f2= p_pos.distance( Vector2D((2-idt)*settings.GAME_WIDTH,settings.GAME_HEIGHT/2.))
    f3 = state.ball.position.distance(Vector2D((2-idt)*settings.GAME_WIDTH,settings.GAME_HEIGHT/2.))
    return [f1,f2,f3]


def entrainement(fn):
    simu = Simulation(team1,team2)
    show_simu(simu)
    # recuperation de tous les etats
    training_states = strat_j1.states
    # sauvegarde dans un fichier
    dump_jsonz(training_states,fn)

def apprentissage(fn):
    ### chargement d'un fichier sauvegarder
    states_tuple = load_jsonz(fn)
    ## Apprentissage de l'arbre
    data_train, data_labels = build_apprentissage(states_tuple,my_get_features)
    dt = apprend_arbre(data_train,data_labels,depth=10)
    # Visualisation de l'arbre
    affiche_arbre(dt)
    genere_dot(dt,"test_arbre.dot")
    return dt

def jouer_arbre(dt):
    ####
    # Utilisation de l'arbre
    ###
    dic = {"Ma Strategie d'attaque":MyAttackStrategy(),"Strategie Intelligente":IntelligentStrategy(),"Ma Strategie de defense":MyDefenseStrategy(),"Ma Strategie de dribble":DribblerStrategy()}
    treeStrat1 = DTreeStrategy(dt,dic,my_get_features)
    treeStrat2 = DTreeStrategy(dt,dic,my_get_features)
    team3 = SoccerTeam("Arbre Team")
    team3.add("Giroud",treeStrat1)
    team3.add("Pogba",treeStrat2)
    simu = Simulation(team2,team3)
    show_simu(simu)

if __name__=="__main__":
    fn = "test_states.jz"
    #if not os.path.isfile(fn):
    entrainement(fn)
    dt = apprentissage(fn)
jouer_arbre(dt)
