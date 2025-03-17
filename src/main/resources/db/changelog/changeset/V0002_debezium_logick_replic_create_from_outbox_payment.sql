-- Включаем логическую репликацию (если не включена)
ALTER SYSTEM SET wal_level = 'logical';

-- Перезагружаем конфигурацию PostgreSQL
SELECT pg_reload_conf();

-- Создаём публикацию, если её нет
DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_publication WHERE pubname = 'debezium_pub') THEN
            CREATE PUBLICATION debezium_pub FOR TABLE public.outbox_payment;
        END IF;
    END $$;
