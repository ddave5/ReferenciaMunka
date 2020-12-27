CREATE TABLE Orders (
 ID integer not null PRIMARY key AUTOINCREMENT,
 Name text not null,
 FoodName text not null,
 Price integer not null,
 ExpectedShipping integer not null,
 Points integer not null
);
