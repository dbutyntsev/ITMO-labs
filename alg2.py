import numpy as np
from scipy.linalg import expm


def compute_matrix_exponential(A_input, t):
    """
    Вычисляет матричную экспоненту e^{At}.
    """
    A = np.array(A_input)

    # Умножаем матрицу A на скаляр t
    At = A * t

    # Вычисляем матричную экспоненту e^{At}
    exp_At = expm(At)

    return exp_At


# --- Пример использования ---

# Задаем матрицу A 
A = [
    [3, 0, 0],
    [0, -1, 0],
    [0, 0, 2]
]

# Задаем скаляр t (например, t = pi/2)
t = np.pi / 2

# Вычисляем результат
result = compute_matrix_exponential(A, t)

print("Исходная матрица A:")
print(np.array(A))

print(f"\nСкаляр t: {t}")

print("\nМатрица e^{At}:")
# Округляем результат для избавления от микроскопического шума операций с плавающей точкой
print(np.round(result, decimals=10))
