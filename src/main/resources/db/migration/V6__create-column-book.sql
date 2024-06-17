ALTER TABLE book
ADD COLUMN description TEXT NOT NULL;

ALTER TABLE book
ADD COLUMN category_id TEXT NOT NULL;

ALTER TABLE book
ADD CONSTRAINT fk_category_id
FOREIGN KEY (category_id) REFERENCES category(id);