# Restaurant & Cafeteria Desktop POS — Full Production-ready System Specification (Java)

**Platform:** Desktop (Windows/Linux)  
**Stack:** Java (OpenJDK 17) + JavaFX UI  
**Database:** SQLite (local file)  
**Packaging:** jpackage native installers (.msi/.deb/.rpm)  
**Locale:** Arabic (default, RTL) + English  
**Currency:** configurable (store setting)

---

## Executive summary
A native desktop POS for small restaurants and cafeterias focused on selling ready-made items (sandwiches, meals, beverages, juices). No customer records. Admin sets prices manually. System is offline-first, optimized for older PCs (Core i3, 4GB RAM). Features: grid-based POS, shifts, receipts + optional KOT (kitchen order ticket), expenses (immediate + recurring, including ingredient spends), reports (sales, expense categories, P&L), backups, settings, and multi-branch support planned for v2.

**Important:** Partial payments are **NOT** allowed in this system — every sale must be paid in full at checkout via one method (Cash or Bank Transfer). Refunds are allowed later if needed.

---

## Users & Roles
- **Admin/Manager:** full access (settings, products, categories, recurring expenses, reports, backups, users).
- **Cashier:** sales, refunds, start/pause/resume/end shift, immediate expenses (create), view alerts, use POS grid, print receipts.

Authentication: username/password hashed with BCrypt. Admins can reset passwords (cannot view plaintext). Optional PIN quick-login for cashiers.

---

## Core Concepts
- No inventory quantity tracking for menu items.
- Expenses include ingredient purchases (treated as expense categories) and contribute to P&L.
- No customer credit/debt or customer module.
- Sales recorded as items with price set by admin.
- KOT (Kitchen Order Ticket) optional; admin can enable/disable and configure printers.

---

## Key Features

### POS / Checkout (Grid-based)
- Grid of buttons for menu items (categories + favorites).
- Add item to order with quantity and optional note (e.g., "no onions").
- Order-level discount (fixed number or percentage input; stored as numeric discount).
- VAT/tax applies on whole order if enabled (admin sets %).
- Payment methods: **Cash** or **Bank Transfer (manual confirmation via recorded transaction/reference)**.
- **Partial payments are NOT allowed.** The system requires full payment at checkout.
- Refunds allowed at any time (no time limit); refund flow logs reason and user.

### KOT (Kitchen Order Ticket)
- Optional toggle in Settings.
- Supports:
  - Two-printer setup: KOT printer (kitchen) + receipt printer (counter).
  - Single-printer setup: print both KOT (simple item list) and full receipt.
- KOT contains only item names, quantities, and special notes (no prices).
- Receipt contains full details (prices, tax, discounts, totals).
- KOT printed when sale is finalized or when "Send to Kitchen" is clicked.

### Products & Categories
- Menu items (products) fields: id, code, name_ar, name_en, category, price, active flag, printer_group (kitchen/counter), image, description, created_by, timestamps.
- Categories manageable by admin.

### Expenses (Immediate & Recurring)
- Admin creates recurring expenses; cashier can create immediate expenses.
- Expense categories (Ingredients/Materials, Utilities, Rent, Salaries, Misc).
- Expenses can include attachments (image/pdf) stored locally (max 10MB).
- Recurrence simple intervals (daily/weekly/monthly/year); occurrences auto-generated.
- Alerts raised for due recurring expenses using admin-set offsets.
- Expense reports by category and date range; included in P&L.

### Shifts
- Cashier starts shift (enter starting cash), pause/resume, end shift (enter actual cash). System computes expected cash and variance.
- Sales and immediate expenses link to current shift.

### Reports & Dashboard
- Dashboard: today's sales, open shifts, alerts, quick actions.
- Reports: daily sales, item sales, category sales, shift reports, expense by category, Profit & Loss (sales - expenses), export CSV/PDF.

### Alerts & Notifications
- Bell icon with colored alerts (configurable colors).
- Alerts for recurring expenses due, failed backups, system info.

### Printing & Peripherals
- ESC/POS thermal printers via Java printing or serial; support for dual printers or single printer printing KOT + receipt.
- Cash drawer via ESC/POS.
- Barcode scanner support optional for packaged items (HID keyboard mode).

### Backups & Restore
- Local DB backup (copy) scheduled daily (default), retention 3 days, restore via admin UI with pre-restore backup auto-created.
- Optional encrypted backups with password.

### Localization & UI
- RTL Arabic-first UI; English toggle.
- Minimalist modern design with clear touch-friendly grid buttons.

---

## Data Model (SQLite tables - simplified)
- users, products, categories, sales, sale_items, payments, shifts, expenses, expense_occurrences, alerts, backups, audit_logs, settings.

---

## Security & Best Practices
- Passwords hashed with BCrypt.
- Prepared statements for DB access.
- Audit logs for critical actions (refunds, shift end, expense deletion).
- Backups stored with restricted file permissions.

---

## Testing & QA
- JUnit 5 unit tests, TestFX UI tests for POS flows, integration tests against temporary SQLite DB.
- Manual hardware acceptance tests for printers and drawer.

---

## Packaging & Distribution
- Build with Gradle (Kotlin DSL), bundle with jpackage for native installers including runtime image (JRE).
- Provide portable build option with JRE included.

---

## v2 Roadmap
- Multi-branch sync & cloud optional.
- Mobile order entry.

