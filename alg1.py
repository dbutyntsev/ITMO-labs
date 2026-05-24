import numpy as np


def check_cayley_hamilton(matrix):
    """
    Функция для численной проверки теоремы Гамильтона-Кэли.
    """
    A = np.array(matrix)
    n = A.shape[0]

    # 1. Вычисляем коэффициенты характеристического полинома p(lambda)
    # Функция np.poly(A) возвращает массив коэффициентов [c_n, c_{n-1}, ..., c_0]
    # для полинома c_n * x^n + c_{n-1} * x^{n-1} + ... + c_0 = 0
    coeffs = np.poly(A)

    # Создаем нулевую матрицу того же размера для накопления результата
    p_A = np.zeros((n, n))

    # 2. Вычисляем p(A) = c_n * A^n + c_{n-1} * A^{n-1} + ... + c_0 * I
    degree = len(coeffs) - 1
    for i, c in enumerate(coeffs):
        power = degree - i
        # np.linalg.matrix_power возводит матрицу в степень power
        # Если power == 0, возвращается единичная матрица I
        p_A += c * np.linalg.matrix_power(A, power)

    return p_A, A


# --- Пример использования ---

# Задаем произвольную квадратную матрицу
A_input = [
    [3, 0, 0],
    [0, -1, 0],
    [0, 0, 2]
]

p_A, A = check_cayley_hamilton(A_input)

print("Исходная матрица A:")
print(A)

print("\nМатрица p(A) после подстановки (с учетом погрешности вычислений):")
# Округляем до 10 знаков после запятой, так как операции с плавающей точкой дают микроскопический шум (например, 1e-15)
print(np.round(p_A, decimals=10))

# Проверяем, действительно ли получилась нулевая матрица (с учетом допустимой погрешности)
is_zero = np.allclose(p_A, np.zeros_like(A))
print(f"\nВыполняется ли теорема Гамильтона-Кэли: {is_zero}")
