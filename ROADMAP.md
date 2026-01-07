# 🗺️ Introduction

This document focuses on what is planned next. The goal is to build a **fully
open-source, privacy-first budgeting application** that can be self-hosted and
extended by the community, with an emphasis on the features below.

---

## 🧱 Phase 1 — Categories, Currencies & Brand

### Categories

- [ ] Custom user-defined categories
- [ ] Hierarchical categories (parent / child)
- [ ] Category rules for faster assignment (by payee or note)
- [ ] Category-based summaries and filters

### Currencies

- [ ] Base currency per budget
- [ ] Account-level currency selection
- [ ] Optional auto-updated FX rates
- [ ] Consistent conversion for reports and balances

### Branding

- [ ] Logo creation
- [ ] App icon set (web + mobile-ready)
- [ ] Basic visual identity guidelines

---

## 👨‍👩‍👧 Phase 2 — Budget Sharing (Family Mode)

### Budgets & Access Control

- [ ] Introduce `Budget` entity
- [ ] Budget ownership model
- [ ] Budget members with roles (owner and member)
- [ ] Multi-budget support per user
- [ ] Activity log for shared actions

### Permissions

- [ ] Shared visibility of accounts & transactions
- [ ] Fine-grained permissions per role
- [ ] Owner-only settings and billing controls

### User Experience

- [ ] Invite members by email or invite code
- [ ] Accept / decline invitations
- [ ] Manage budget members and roles
- [ ] Leave budget functionality
- [ ] Optional per-member visibility labels

---

## 🔁 Phase 3 — Recurring Transactions & Subscriptions

### Subscriptions

- [ ] Subscription entity (amount, interval, account, category)
- [ ] Monthly / yearly recurrence support
- [ ] Upcoming payment tracking
- [ ] Status: active, paused, canceled
- [ ] Trial and intro-period support

### Automation

- [ ] Scheduled job for auto-generating transactions
- [ ] Monthly subscription cost overview
- [ ] Reminder notifications for upcoming bills

---

## 📊 Phase 4 — Dashboard & Visualization

### Visual Components

- [ ] Income vs expenses charts
- [ ] Category distribution charts
- [ ] Cashflow timeline
- [ ] Subscription cost overview
- [ ] Currency-aware summaries

### UX Improvements

- [ ] Interactive filtering
- [ ] Time-range selection
- [ ] Saved dashboard presets
- [ ] Responsive dashboard layout

---

## 🏦 Phase 5 — Bank Integrations

### Europe (Open Banking / PSD2)

- [ ] Integrate GoCardless Bank Data (Nordigen)
- [ ] Import accounts & transactions
- [ ] Periodic synchronization

### USA

- [ ] Plaid integration

### Asia

- [ ] Research region-specific banking providers

### Data Handling

- [ ] Transaction deduplication logic
- [ ] External transaction reference storage

---

---

## 🧭 Long-Term Ideas

- **Very important (not yet phased)**
- [ ] AI-powered spending insights and anomaly detection
- [ ] Overspending alerts with configurable thresholds
- [ ] Subscription reduction tips and unused-service detection
- [ ] Savings projections and goal forecasting

### Additional Long-Term Ideas

- [ ] Mobile application for iOS and Android
