# Лабораторная работа №3. Работа с базой данных.
Код приложения написан на языке Java и использует Android SDK.

## Инструкция по использованию приложения
Первый экран приложения содержит три кнопки:
1. [Показать записи](#кнопка-показать-записи)
2. [Добавить запись](#кнопка-добавить-запись)
3. [Обновить запись](#кнопка-обновить-запись)
<p align="center">
<img src="https://sun9-48.userapi.com/impg/YMu6lzM7bye0wx48glkBlIqJCqlp_PLTsdhNbg/PJhKN9upbYA.jpg?size=720x1520&quality=95&sign=b01da97fbce0d970ea4bf5016b2fc8f6&type=album" width="250" height="500"> 
</p>

## Кнопка "Показать записи"
При нажатии открывается второй экран, выводящий информацию из таблицы «Одногруппники» в удобном для восприятия формате.
При запуске приложение:
1. Создает БД, если ее не существует.
```java
 private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FULL_NAME + " TEXT NOT NULL, " +
                    COLUMN_TIMESTAMP + " TIME DEFAULT CURRENT_TIME" + ");";
```
3. Создает таблицу «Одногруппники», содержащую поля:
- ID;
- ФИО;
- Время добавления записи.
```java
    public static final String TABLE_NAME = "groupmates";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_TIMESTAMP = "timestamp";
```
5. Удаляет все записи из БД, а затем вносит 5 записей об одногруппниках.
```java
private void initializeData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME);
        String[] fullNames = {
                "Новикова Анастасия Евгеньевна",
                "Петров Пётр Петрович",
                "Сидоров Сидор Сидорович",
                "Никитин Никита Никитич",
                "Сергеев Сергей Сергеевич"
        };
        for (String name : fullNames) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FULL_NAME, name);
            db.insert(TABLE_NAME, null, values);
        }
    }
```
<p align="center">
<img src="https://sun9-68.userapi.com/impg/SedXUCsjy13eFmmftFaLsKFxqQv_FR7I5_B1gA/e6VwYNDGh6I.jpg?size=720x1520&quality=95&sign=a8d2836393119552582c9c70d104f0a8&type=album" width="250" height="500"> 
</p>

## Кнопка "Добавить запись"
При нажатии вносит еще одну запись в таблицу.
```java
addGroupmateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME + " (" +
                            DatabaseHelper.COLUMN_FULL_NAME + ") VALUES ('Новый одногруппник');");
                }
            });
```
<p align="center">
<img src="https://sun9-64.userapi.com/impg/0SCC_3poh1m0C4MDu_w0MpZAbqOnrqWXESxEDQ/4LZJLZPTP1U.jpg?size=720x1520&quality=95&sign=ac79f6679f9dd2f4ab4852ccbbda7a3f&type=album" width="250" height="500"> 
</p>

## Кнопка "Обновить запись"
При нажатии заменяет ФИО в последней внесенной записи на Иванов Иван Иванович.
```java
updateGroupmateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME +
                            " SET " + DatabaseHelper.COLUMN_FULL_NAME + " = 'Иванов Иван Иванович' " +
                            "WHERE " + DatabaseHelper.COLUMN_ID + " = (SELECT MAX(" + DatabaseHelper.COLUMN_ID + ") FROM " + DatabaseHelper.TABLE_NAME + ");");
                }
            });
```
<p align="center">
<img src="https://sun9-17.userapi.com/impg/s9e8xakkwlUqfaW_KRNIhJ3mi7Ypoi5V2gid6Q/NRfknKqT6N4.jpg?size=720x1520&quality=95&sign=74e61064f2d2607772d9644211d69b6a&type=album" width="250" height="500"> 
</p>
