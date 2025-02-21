import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from sklearn import tree
from sklearn import neural_network as neurona
from sklearn import metrics as m



"""
ATRIBUTOS 
Marital Status	:
1 – single 2 – married 3 – widower 4 – divorced 5 – facto union 6 – legally separated
Course :
33 - Biofuel Production Technologies 171 - Animation and Multimedia Design 
8014 - Social Service (evening attendance) 9003 - Agronomy 9070 - Communication Design 
9085 - Veterinary Nursing 9119 - Informatics Engineering 9130 - Equinculture 9147 - Management 
9238 - Social Service 9254 - Tourism 9500 - Nursing 9556 - Oral Hygiene 
9670 - Advertising and Marketing Management 9773 - Journalism and Communication 
9853 - Basic Education 9991 - Management (evening attendance)
Attendance :
1 – daytime 0 - evening
Previous qualification :
1 - Secondary education 2 - Higher education - bachelor's degree 
3 - Higher education - degree 4 - Higher education - master's 5 - Higher education - doctorate 
6 - Frequency of higher education 9 - 12th year of schooling - not completed 
10 - 11th year of schooling - not completed 12 - Other - 11th year of schooling 
14 - 10th year of schooling 15 - 10th year of schooling - not completed
19 - Basic education 3rd cycle (9th/10th/11th year) or equiv. 
38 - Basic education 2nd cycle (6th/7th/8th year) or equiv. 39 - Technological specialization course 
40 - Higher education - degree (1st cycle) 42 - Professional higher technical course 
43 - Higher education - master (2nd cycle)	
Nacionality:
1 - Portuguese; 2 - German; 6 - Spanish; 11 - Italian; 13 - Dutch; 14 - English; 17 - Lithuanian; 
21 - Angolan; 22 - Cape Verdean; 24 - Guinean; 25 - Mozambican; 26 - Santomean; 32 - Turkish; 
41 - Brazilian; 62 - Romanian; 100 - Moldova (Republic of); 101 - Mexican; 103 - Ukrainian; 
105 - Russian; 108 - Cuban; 109 - Colombian
Gender :
1 – male 0 – female
International :
1 – yes 0 – no
Target (DATO OBJETIVO):
Target. The problem is formulated as a three category classification task (dropout, enrolled, and graduate) 
at the end of the normal duration of the course
"""

def Arbol (x,y):
    

    #Preguntar que cambia modificar el valor random_state
    X_train, X_test, y_train, y_test = train_test_split(x, y, random_state=1)

    Arbol_Decision = DecisionTreeClassifier()
    Arbol_Decision.fit(X_train,y_train)

    y_pred = Arbol_Decision.predict(X_test)


    plt.figure(figsize=(20, 10))

    tree.plot_tree(Arbol_Decision, 
            feature_names=x.columns,
            class_names=Arbol_Decision.classes_,
            filled=True)

    plt.show()
    
    tn, fp, fn, tp = m.confusion_matrix(y_test, y_pred).ravel()

    print(f"Verdaderos Positivos (TP): {tp}")
    print(f"Falsos Positivos (FP): {fp}")
    print(f"Verdaderos Negativos (TN): {tn}")
    print(f"Falsos Negativos (FN): {fn}")

    


def Perceptron_Multicapa (x,y):
   

    #Preguntar que cambia modificar el valor random_state
    X_train, X_test, y_train, y_test = train_test_split(x, y, random_state=1)

    perceptron= neurona.MLPClassifier(hidden_layer_sizes=24,max_iter=670,tol=0.001)
    perceptron.fit(X_train,y_train)

    y_pred= perceptron.predict(X_test)

    tn, fp, fn, tp = m.confusion_matrix(y_test, y_pred).ravel()
    

    print(f"Verdaderos Positivos (TP): {tp}")
    print(f"Falsos Positivos (FP): {fp}")
    print(f"Verdaderos Negativos (TN): {tn}")
    print(f"Falsos Negativos (FN): {fn}")







datos = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract5\data.csv',sep=";")

df_filtrado= datos[['Marital status', 'Course', 'Daytime/evening attendance	', 'Previous qualification', 'Nacionality', 'Gender', 'International', 'Target']]
df_filtrado2 = df_filtrado.drop(df_filtrado[df_filtrado['Target'] == 'Enrolled'].index)

df= df_filtrado2.rename(columns={'Daytime/evening attendance	': 'Attendance'})
x= df.drop('Target',axis=1)
y= df['Target']
#print(df)
Arbol(x,y)
print("\n")
Perceptron_Multicapa(x,y)





