import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split, GridSearchCV,cross_validate,StratifiedKFold
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from sklearn import tree
from sklearn import metrics as m

datos = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract5\data.csv',sep=";")

df_filtrado= datos[['Marital status', 'Course', 'Daytime/evening attendance	', 'Previous qualification', 'Nacionality', 'Gender', 'International','Displaced','Educational special needs','Age at enrollment', 'Target']]
df_filtrado2 = df_filtrado.drop(df_filtrado[df_filtrado['Target'] == 'Enrolled'].index)
df_filtrado2['Target'] = df_filtrado2['Target'].map({"Dropout": 0, "Graduate": 1})

df= df_filtrado2.rename(columns={'Daytime/evening attendance	': 'Attendance'})

X= df.drop('Target',axis=1)
y= df['Target']
print(df)
X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=1)

param_grid = {
 "criterion": ["entropy", "gini"],
 "max_depth": [5, 10 , 15, 20, 25, 30, 35],
 "min_samples_split": [2, 3, 4, 5],
 "min_samples_leaf": [2, 3, 4 ]
}

Arbol_Decision = DecisionTreeClassifier()


grid_search = GridSearchCV(Arbol_Decision,param_grid=param_grid,cv=10,n_jobs=-1,scoring="accuracy")
grid_search.fit(X_train,y_train)


print("Mejores hiperparámetros encontrados:")
print(grid_search.best_params_)
print("Mejor puntuación de validación cruzada:")
print(grid_search.best_score_)


best_param = grid_search.best_params_

Mejor_Arbol_Decision=DecisionTreeClassifier(**best_param)
Mejor_Arbol_Decision.fit(X_train,y_train)
y_pred= Mejor_Arbol_Decision.predict(X_test)


kross_validation= StratifiedKFold(n_splits=10,shuffle= True)
scores_tree = cross_validate(Mejor_Arbol_Decision, X, y,cv=kross_validation,scoring='accuracy',)
print("Scores del árbol clasificador: \n", scores_tree)

print("\n")
accuracy = accuracy_score(y_test, y_pred)
print("Accuracy/Arbol:", accuracy)

precision= m.precision_score(y_test,y_pred,average='weighted',zero_division=1)
print("Precision/Arbol:", precision)

fm= m.f1_score(y_test, y_pred,average='weighted')
print("F1Measure/Arbol: ",fm)

print("\n")


matriz_confusion=m.confusion_matrix(y_test, y_pred)

tn, fp, fn, tp = matriz_confusion.ravel()

print(f"Verdaderos Positivos (TP): {tp}")
print(f"Falsos Positivos (FP): {fp}")
print(f"Verdaderos Negativos (TN): {tn}")
print(f"Falsos Negativos (FN): {fn}")

matriz_confusion_display= m.ConfusionMatrixDisplay(confusion_matrix=matriz_confusion,display_labels=["Dropout","Graduate"])
matriz_confusion_display.plot()
plt.show()

plt.figure(figsize=(20, 10))

tree.plot_tree(Mejor_Arbol_Decision, 
            feature_names=list(X.columns),
            class_names=['Dropout','Graduate'],
            filled=True)

plt.show()


