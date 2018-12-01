-- FIND_ANSWER_SEQUENCE_BY_QUESTION_ID
SELECT * FROM ANSWERS_SEQUENCE WHERE QUESTION_ID = ?;

-- ADD_ANSWER_SEQUENCE
INSERT INTO ANSWERS_SEQUENCE (question_id, item_1, item_2, item_3, item_4)
VALUES (?, ?, ?, ?, ?);

-- EDIT_ANSWER_SEQUENCE
UPDATE ANSWERS_SEQUENCE
SET ITEM_1 = ?, ITEM_2 = ?, ITEM_3 = ?, ITEM_4 = ?
WHERE QUESTION_ID = ?;

-- DELETE_ANSWER_SEQUENCE
DELETE FROM ANSWERS_SEQUENCE WHERE QUESTION_ID = ?;