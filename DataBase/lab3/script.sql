ALTER TABLE Saga ADD COLUMN total_students_count INTEGER DEFAULT 0;
CREATE OR REPLACE FUNCTION update_saga_students_count()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        UPDATE Saga 
        SET total_students_count = total_students_count + 1
        WHERE id = NEW.saga_id;
    ELSIF (TG_OP = 'DELETE') THEN
        UPDATE Saga 
        SET total_students_count = total_students_count - 1
        WHERE id = OLD.saga_id;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_after_saga_study_change
AFTER INSERT OR DELETE ON Saga_Study
FOR EACH ROW
EXECUTE FUNCTION update_saga_students_count();
