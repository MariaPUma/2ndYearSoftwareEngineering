import sklearn.datasets
import sklearn.model_selection
import sklearn.neural_network
import sklearn.metrics
iris = sklearn.datasets.load_iris()
X = iris.data
y = iris.target
Xtrain, Xtest, ytrain, ytest =sklearn.model_selection.train_test_split(X, y,
random_state=45)
hls = (6,)
act = 'tanh'
slv='adam'
lri = 0.01
mxi = 600
model =sklearn.neural_network.MLPClassifier(hidden_layer_sizes =
hls, activation=act, solver=slv, learning_rate_init=lri,
max_iter=mxi )
model.fit(Xtrain, ytrain)
y_model=model.predict(Xtest)
print('The confusion matrix is:')
print(sklearn.metrics.confusion_matrix(ytest, y_model))