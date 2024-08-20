import sys
import joblib
import numpy as np

# Load the model
model = joblib.load('model.pkl')

# Make a prediction
if __name__ == "__main__":
    input_data = np.array([float(arg) for arg in sys.argv[1:]]).reshape(-1, 1)
    prediction = model.predict(input_data)
    print(prediction[0])
