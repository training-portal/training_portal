-- FIND_ANSWER_SEQUENCE_BY_QUESTION_ID
SELECT SEQ.ANSWER_SEQUENCE_ID, SEQ.QUESTION_ID, CORRECT_LISTS.ITEM
FROM CORRECT_LISTS INNER JOIN ANSWERS_SEQUENCE SEQ
ON CORRECT_LISTS.ANSWER_SEQUENCE_ID = SEQ.ANSWER_SEQUENCE_ID
WHERE SEQ.QUESTION_ID = ?;

-- ADD_ANSWER_SEQUENCE
INSERT INTO ANSWERS_SEQUENCE (QUESTION_ID) VALUES (?);
INSERT INTO CORRECT_LISTS (ITEM, ANSWER_SEQUENCE_ID) VALUES (?, ?);

-- EDIT_ANSWER_SEQUENCE
UPDATE ANSWERS_SEQUENCE SET QUESTION_ID = ? WHERE ANSWER_SEQUENCE_ID = ?;
UPDATE CORRECT_LISTS SET ANSWER_SEQUENCE_ID = ?, ITEM = ? WHERE CORRECT_LIST_ID = ?;

-- DELETE_ANSWER_SEQUENCE
DELETE FROM ANSWERS_SEQUENCE WHERE ANSWER_SEQUENCE_ID = ?;
DELETE FROM CORRECT_LISTS WHERE ANSWER_SEQUENCE_ID = ?;