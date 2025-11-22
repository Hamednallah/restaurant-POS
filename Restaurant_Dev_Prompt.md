# Restaurant POS — Developer Prompt (Java Desktop) for LLM

System prompt:
You are an expert Java full-stack engineer. Generate a complete desktop POS app using Java 17 + JavaFX + SQLite. Follow TDD: create failing unit tests then implementations. Produce incremental commits with messages. Provide Gradle Kotlin DSL build, migrations, seed data, TestFX UI tests, and jpackage scripts. Optimize for low-RAM machines. Implement features as specified in the system spec.

User prompt:
Build Restaurant & Cafeteria Desktop POS with:
- Grid-based POS, admin defined menu items and categories.
- No customer module. No stock quantities.
- Payments: Cash, Bank Transfer (store tx ref optional).
- VAT on whole order (toggleable), order-level discounts.
- KOT optional (two-printer or single-printer printing KOT+receipt).
- Expenses immediate and recurring; categories; attachments; reports; alerts.
- Shifts, backups, reports, localization Arabic/English.
- **Partial payments are NOT allowed.**
Deliverables: runnable Gradle project scaffold, migrations, seed CSVs, packaging scripts, README, developer instruction, and prompts for further LLM development.