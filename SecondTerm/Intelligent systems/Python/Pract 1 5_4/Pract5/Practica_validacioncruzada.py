import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from sklearn import tree
from sklearn import neural_network as neurona
from sklearn import metrics as m
from sklearn.preprocessing import label_binarize
from sklearn.model_selection import cross_val_score
datos = pd.read_csv(r'Pract5\data.csv',sep=";")

df_filtrado= datos[['Marital status', 'Course', 'Daytime/evening attendance	', 'Previous qualification', 'Nacionality', 'Gender', 'International', 'Target']]
df_filtrado2 = df_filtrado.drop(df_filtrado[df_filtrado['Target'] == 'Enrolled'].index)

df= df_filtrado2.rename(columns={'Daytime/evening attendance	': 'Attendance'})

X= df.drop('Target',axis=1)
y= df['Target']

X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=1)


#-----------------------------------------------------------------------------------------------------------------------------
#                               ÁRBOL
#-----------------------------------------------------------------------------------------------------------------------------



Arbol_Decision = DecisionTreeClassifier()
Arbol_Decision.fit(X_train,y_train)

y_pred_tree = Arbol_Decision.predict_proba(X_test)

#-----------------------------------------------------------------------------------------------------------------------------
#                                   PERCEPTRÓN
#-----------------------------------------------------------------------------------------------------------------------------


perceptron= neurona.MLPClassifier(hidden_layer_sizes=24,max_iter=670,tol=0.001)
perceptron.fit(X_train,y_train)

y_pred_perceptron= perceptron.predict_proba(X_test)

#-----------------------------------------------------------------------------------------------------------------------------

scores_tree = cross_val_score(Arbol_Decision, X, y, cv=10)
print("Scores del árbol clasificador:", scores_tree)
print("\nPrecisión promedio del árbol clasificador:", scores_tree.mean())

# Validación cruzada para el perceptrón multicapa
scores_mlp = cross_val_score(perceptron, X, y, cv=10)
print("\nScores del perceptrón multicapa:", scores_mlp)
print("\nPrecisión promedio del perceptrón multicapa:", scores_mlp.mean())