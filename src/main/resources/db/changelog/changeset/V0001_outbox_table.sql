CREATE TABLE IF NOT EXISTS outbox_payment (
    id uuid PRIMARY KEY UNIQUE,
    aggregate_id BIGINT NOT NULL,
    event_type TEXT NOT NULL,
    payload jsonb NOT NULL,
    created_at timestamp with time zone DEFAULT now()
);

--   таблица не верна. она для другого варианта