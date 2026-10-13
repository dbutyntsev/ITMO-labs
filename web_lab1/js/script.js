"use strict";

/* =========================================================
   ЧАСТЬ 0. НАХОДИМ ЭЛЕМЕНТЫ СТРАНИЦЫ
   Ищем элементы один раз и сохраняем в переменные,
   чтобы потом не искать их заново.
   ========================================================= */

const form = document.getElementById("point-form");
const yInput = document.getElementById("y");
const errorBox = document.getElementById("error-box");
const resultsBody = document.getElementById("results-body");
const xButtons = document.querySelectorAll(".x-btn");
const rRadios = document.querySelectorAll('input[name="r"]');

const canvas = document.getElementById("graph");
const ctx = canvas.getContext("2d");   // «кисть», которой рисуем на холсте

const STORAGE_KEY = "lab1-results";    // имя, под которым храним данные в LocalStorage

let selectedX = null;                  // выбранный X (пока ничего не выбрано)
let results = loadResults();           // все результаты проверок


/* =========================================================
   ЧАСТЬ 1. КНОПКИ X
   При клике запоминаем значение и подсвечиваем кнопку.
   ========================================================= */

xButtons.forEach(function (button) {
    button.addEventListener("click", function () {
        selectedX = parseFloat(button.dataset.x);   // читаем data-x и делаем числом

        // снимаем подсветку со всех кнопок и ставим на нажатую
        xButtons.forEach(function (b) {
            b.classList.remove("selected");
        });
        button.classList.add("selected");

        errorBox.textContent = "";
    });
});


/* =========================================================
   ЧАСТЬ 2. ПРОВЕРКА ПОПАДАНИЯ
   ========================================================= */

function isHit(x, y, r) {
    // II четверть: прямоугольник
    if (x <= 0 && y >= 0) {
        return x >= -r && y <= r;
    }
    // III четверть: четверть круга
    if (x <= 0 && y <= 0) {
        return x * x + y * y <= r * r;
    }
    // IV четверть: треугольник, точка выше прямой y = x - R/2
    if (x >= 0 && y <= 0) {
        return y >= x - r / 2;
    }
    // I четверть: пусто
    return false;
}


/* =========================================================
   ЧАСТЬ 3. ВАЛИДАЦИЯ
   Каждая функция возвращает текст ошибки или null, если всё хорошо.
   ========================================================= */

function validateX(x) {
    if (x === null) {
        return "Выберите значение X.";
    }
    return null;
}

function validateY(text) {
    text = text.trim().replace(",", ".");   // убираем пробелы, запятую меняем на точку

    if (text === "") {
        return "Введите значение Y.";
    }

    // Вся строка целиком должна быть числом: знак, цифры, может быть дробная часть.
    // "1abc", "1..2", "abc", "1e2" сюда не пройдут.
    if (!/^[+-]?\d+(\.\d+)?$/.test(text)) {
        return "Y должен быть числом, например 1.5 или -2.";
    }

    // Проверяем диапазон по целой части строки, а не по числу:
    // parseFloat("4.99999999999999999") даёт 5, и такое значение было бы ошибочно отклонено.
    const negative = text.startsWith("-");
    const intPart = parseInt(text.replace(/^[+-]/, "").split(".")[0], 10);

    if (negative && intPart >= 3) {      // y <= -3
        return "Y должен быть больше -3 и меньше 5.";
    }
    if (!negative && intPart >= 5) {     // y >= 5
        return "Y должен быть больше -3 и меньше 5.";
    }
    return null;
}

function validateR(r) {
    if (r === null) {
        return "Выберите значение R.";
    }
    return null;
}

function getSelectedR() {
    const checked = document.querySelector('input[name="r"]:checked');
    if (checked === null) {
        return null;
    }
    return parseFloat(checked.value);
}


/* =========================================================
   ЧАСТЬ 4. ОТПРАВКА ФОРМЫ
   ========================================================= */

form.addEventListener("submit", function (event) {
    event.preventDefault();    // не отправлять форму и не перезагружать страницу

    const r = getSelectedR();

    // проверяем поля по очереди, при первой ошибке показываем её и выходим
    const error = validateX(selectedX) || validateY(yInput.value) || validateR(r);
    if (error !== null) {
        errorBox.textContent = error;
        return;
    }
    errorBox.textContent = "";

    const yText = yInput.value.trim().replace(",", ".");
    const y = parseFloat(yText);

    const result = {
        x: selectedX,
        y: yText,              // храним строкой, чтобы показать ровно то, что ввёл пользователь
        r: r,
        hit: isHit(selectedX, y, r),
        time: Date.now()       // момент времени в миллисекундах, не зависит от часового пояса
    };

    results.push(result);
    saveResults();
    addRow(result);
    drawGraph();
});


/* =========================================================
   ЧАСТЬ 5. ТАБЛИЦА РЕЗУЛЬТАТОВ
   ========================================================= */

function addRow(result) {
    const row = document.createElement("tr");

    const cells = [
        result.x,
        result.y,
        result.r,
        result.hit ? "Да" : "Нет",
        formatDate(result.time)
    ];

    cells.forEach(function (value) {
        const cell = document.createElement("td");
        cell.textContent = value;
        row.appendChild(cell);
    });

    resultsBody.prepend(row);  // новые результаты — сверху
}

function showAllRows() {
    resultsBody.innerHTML = "";
    results.forEach(addRow);
}


/* =========================================================
   ЧАСТЬ 6. LOCALSTORAGE И ДАТА
   ========================================================= */

function saveResults() {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(results));
}

function loadResults() {
    const saved = localStorage.getItem(STORAGE_KEY);
    if (saved === null) {
        return [];
    }
    try {
        return JSON.parse(saved);
    } catch (e) {
        return [];             // если в хранилище мусор — начинаем с пустого списка
    }
}

// Число миллисекунд превращаем в текст только при показе:
// "ru-RU" — русский формат, часовой пояс браузер берёт с устройства.
function formatDate(time) {
    return new Date(time).toLocaleString("ru-RU");
}


/* =========================================================
   ЧАСТЬ 7. CANVAS
   ========================================================= */

const W = canvas.width;
const H = canvas.height;
const CX = W / 2;          // центр координат на холсте
const CY = H / 2;
const R_PX = 150;          // сколько пикселей на рисунке занимает R

// Перевод математических координат в пиксели холста.
// У canvas ось Y направлена вниз, поэтому для y стоит минус.
function toPixelX(x, r) {
    return CX + (x / r) * R_PX;
}
function toPixelY(y, r) {
    return CY - (y / r) * R_PX;
}

function drawArea() {
    ctx.fillStyle = "#3399ff";

    // прямоугольник: левый верхний угол (CX - R, CY - R), ширина R, высота R
    ctx.fillRect(CX - R_PX, CY - R_PX, R_PX, R_PX);

    // четверть круга: от угла PI/2 (вниз) до PI (влево)
    ctx.beginPath();
    ctx.moveTo(CX, CY);
    ctx.arc(CX, CY, R_PX, Math.PI / 2, Math.PI);
    ctx.closePath();
    ctx.fill();

    // треугольник: (0;0), (R/2;0), (0;-R/2)
    ctx.beginPath();
    ctx.moveTo(CX, CY);
    ctx.lineTo(CX + R_PX / 2, CY);
    ctx.lineTo(CX, CY + R_PX / 2);
    ctx.closePath();
    ctx.fill();
}

function drawAxes(r) {
    ctx.strokeStyle = "#000000";
    ctx.fillStyle = "#000000";
    ctx.lineWidth = 1;
    ctx.font = "13px Arial";

    // ось X со стрелкой
    ctx.beginPath();
    ctx.moveTo(10, CY);
    ctx.lineTo(W - 10, CY);
    ctx.lineTo(W - 18, CY - 5);
    ctx.moveTo(W - 10, CY);
    ctx.lineTo(W - 18, CY + 5);
    ctx.stroke();
    ctx.fillText("x", W - 18, CY - 10);

    // ось Y со стрелкой
    ctx.beginPath();
    ctx.moveTo(CX, H - 10);
    ctx.lineTo(CX, 10);
    ctx.lineTo(CX - 5, 18);
    ctx.moveTo(CX, 10);
    ctx.lineTo(CX + 5, 18);
    ctx.stroke();
    ctx.fillText("y", CX + 10, 18);

    // подписи делений: буквы, если R не выбран, иначе числа
    const full = r === null ? "R" : String(r);
    const half = r === null ? "R/2" : String(r / 2);
    const ticks = [
        [-1, "-" + full],
        [-0.5, "-" + half],
        [0.5, half],
        [1, full]
    ];

    ticks.forEach(function (tick) {
        const k = tick[0];
        const label = tick[1];
        const px = CX + k * R_PX;
        const py = CY - k * R_PX;

        // деление на оси X
        ctx.beginPath();
        ctx.moveTo(px, CY - 4);
        ctx.lineTo(px, CY + 4);
        ctx.stroke();
        ctx.fillText(label, px - 10, CY - 8);

        // деление на оси Y
        ctx.beginPath();
        ctx.moveTo(CX - 4, py);
        ctx.lineTo(CX + 4, py);
        ctx.stroke();
        ctx.fillText(label, CX + 8, py + 4);
    });
}

function drawPoint(result) {
    ctx.beginPath();
    ctx.arc(toPixelX(result.x, result.r), toPixelY(parseFloat(result.y), result.r), 4, 0, 2 * Math.PI);
    ctx.fillStyle = result.hit ? "#1a7f37" : "#c0262d";   // зелёная — попала, красная — нет
    ctx.fill();
}

// Полная перерисовка: очистить, нарисовать область, оси и точки текущего R
function drawGraph() {
    const r = getSelectedR();

    ctx.clearRect(0, 0, W, H);
    drawArea();
    drawAxes(r);

    if (r !== null) {
        results
            .filter(function (result) { return result.r === r; })
            .forEach(drawPoint);
    }
}

// при смене R перерисовываем график под новый радиус
rRadios.forEach(function (radio) {
    radio.addEventListener("change", drawGraph);
});

/* =========================================================
   КНОПКА «ОЧИСТИТЬ РЕЗУЛЬТАТЫ»
   ========================================================= */

const clearBtn = document.getElementById("clear-btn");

clearBtn.addEventListener("click", function () {
    if (results.length === 0) {
        return;                // очищать нечего
    }
    if (!confirm("Удалить все результаты проверок?")) {
        return;                // пользователь нажал «Отмена»
    }
    results = [];              // очищаем массив
    saveResults();             // сохраняем пустой массив в LocalStorage
    showAllRows();             // таблица станет пустой
    drawGraph();               // с графика пропадут точки
});

/* =========================================================
   ЗАПУСК ПРИ ЗАГРУЗКЕ СТРАНИЦЫ
   ========================================================= */

showAllRows();
drawGraph();