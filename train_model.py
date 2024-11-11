import numpy as np
from sklearn.linear_model import LinearRegression
import joblib

# Train a simple model
X = np.array([[1], [2], [3], [4], [5]])  # features
y = np.array([2, 3, 4, 5, 6])             # target: y = x + 1

# Fit the model
model = LinearRegression().fit(X, y)

# Save the model
joblib.dump(model, 'model.pkl')