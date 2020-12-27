CREATE TABLE Series (
 ID integer not null PRIMARY key AUTOINCREMENT,
 Title text not null,
 Category text not null,
 Season integer not null,
 Episodes integer not null,
 Priority integer not null,
 UNIQUE(Title)
);
