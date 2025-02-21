import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn import metrics as m
from sklearn import tree
from sklearn.metrics import accuracy_score
from sklearn import neural_network as neurona
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import label_binarize



datos = pd.read_csv(r'\data.csv',sep=";")

df_filtrado= datos[['Marital status', 'Course', 'Daytime/evening attendance	', 'Previous qualification', 'Nacionality', 'Gender', 'International','Displaced','Educational special needs','Age at enrollment', 'Target']]
df_filtrado2 = df_filtrado.drop(df_filtrado[df_filtrado['Target'] == 'Enrolled'].index)
df_filtrado2['Target'] = df_filtrado2['Target'].map({"Dropout": 0, "Graduate": 1})

df= df_filtrado2.rename(columns={'Daytime/evening attendance	': 'Attendance'})

X= df.drop('Target',axis=1)
y= df['Target']
#print(df)
X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=1)

#y_test_binarized = (label_binarize(y_test, classes=np.unique(y_test)))

#-----------------------------------------------------------------------------------------------------------------------------
#                               ÁRBOL
#-----------------------------------------------------------------------------------------------------------------------------



Arbol_Decision = DecisionTreeClassifier(criterion='gini',max_depth=5,min_samples_leaf=3,min_samples_split=3)
Arbol_Decision.fit(X_train,y_train)

y_pred_tree = Arbol_Decision.predict_proba(X_test)

#-----------------------------------------------------------------------------------------------------------------------------
#                                   PERCEPTRÓN
#-----------------------------------------------------------------------------------------------------------------------------


perceptron= neurona.MLPClassifier(activation='tanh',alpha=0.001,hidden_layer_sizes=(100,100),learning_rate='constant',max_iter=670,random_state=42)
perceptron.fit(X_train,y_train)

y_pred_perceptron= perceptron.predict_proba(X_test)

#-----------------------------------------------------------------------------------------------------------------------------

fpr_tree, tpr_tree, _ = m.roc_curve(y_test, y_pred_tree[:, 1])
fpr_perceptron, tpr_perceptron, _ = m.roc_curve(y_test, y_pred_perceptron[:, 1])

auc_tree = m.auc(fpr_tree, tpr_tree)
auc_mlp = m.auc(fpr_perceptron, tpr_perceptron)
plt.figure(figsize=(8, 6))
plt.plot(fpr_tree, tpr_tree, label=f'Árbol de clasificación (área = {auc_tree:.2f})')
plt.plot(fpr_perceptron, tpr_perceptron, label=f'Perceptrón multicapa (área = {auc_mlp:.2f})')
plt.plot([0, 1], [0, 1], 'k--')
plt.xlabel('Tasa de Falsos Positivos')
plt.ylabel('Tasa de Verdaderos Positivos')
plt.title('Comparación de Curvas ROC')
plt.legend()
plt.show()