CREATE SEQUENCE users_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE users
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(256) NOT NULL,
    avatar_link VARCHAR(256)
);


CREATE SEQUENCE notes_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE notes
(
    id              INTEGER PRIMARY KEY,
    text            TEXT    NOT NULL,
    author_id       INTEGER NOT NULL,
    createdDateTime BIGINT  NOT NULL DEFAULT NOW(),
    editedDateTime  BIGINT
)
