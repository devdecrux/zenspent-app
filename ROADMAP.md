# 🗺️ Project Roadmap

This document outlines the current state of the project and the planned roadmap.
The goal is to build a **fully open-source, privacy-first budgeting application**
that can be self-hosted and extended by the community.

---

## ✅ Implemented

### Core Architecture

- Spring Boot backend
- Vue.js frontend
- PostgreSQL database
- Traefik reverse proxy for local development
- Separate frontend and backend services with unified entry point

### Authentication & Security

- Spring Security with session-based authentication
- `JSESSIONID` cookie handling
- CSRF protection using `XSRF-TOKEN`
- Login flow implemented

### Core Features

- User accounts
- Financial accounts
- Transactions (income & expenses)
- Automatic account balance updates based on transactions

---

## 🚧 Phase 1 — Stabilization & Foundations

### Infrastructure & Development Experience

- [ ] Fix local development reverse-proxy setup (single host & port)
- [ ] Improve cookie and CSRF handling in dev mode
- [ ] Separate dev / prod configuration via Spring profiles & Vue envs
- [x] Add API versioning (`/api/v1`)
- [ ] Improve error handling & validation responses

### Codebase Improvements

- [ ] Refactor domain model boundaries
- [ ] Introduce consistent DTO & mapper strategy
- [ ] Add basic integration tests

---

## 👨‍👩‍👧 Phase 2 — Budget Sharing (Family Mode)

### Budgets & Access Control

- [ ] Introduce `Budget` entity
- [ ] Budget ownership model
- [ ] Budget members with roles (owner, member)
- [ ] Multi-budget support per user

### Permissions

- [ ] Shared visibility of accounts & transactions
- [ ] Owner-only modification rights
- [ ] Member read-only access

### User Experience

- [ ] Invite members by email or invite code
- [ ] Manage budget members
- [ ] Leave budget functionality

---

## 🗂️ Phase 3 — Categories

### Category System

- [ ] Default categories (Food, Rent, Utilities, etc.)
- [ ] Custom user-defined categories
- [ ] Hierarchical categories (parent / child)

### Transactions

- [ ] Assign category to transactions
- [ ] Filter transactions by category
- [ ] Category-based summaries

---

## 🔁 Phase 4 — Recurring Transactions & Subscriptions

### Subscriptions

- [ ] Subscription entity (amount, interval, account, category)
- [ ] Monthly / yearly recurrence support
- [ ] Upcoming payment tracking

### Automation

- [ ] Scheduled job for auto-generating transactions
- [ ] Monthly subscription cost overview

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

## 📊 Phase 6 — Insights & Recommendations

### Analytics

- [ ] Spending trends
- [ ] Monthly summaries
- [ ] Category comparisons

### Smart Suggestions

- [ ] Overspending alerts
- [ ] Subscription reduction tips
- [ ] Savings projections

---

## 📈 Phase 7 — Dashboard & Visualization

### Visual Components

- [ ] Income vs expenses charts
- [ ] Category distribution charts
- [ ] Cashflow timeline
- [ ] Subscription cost overview

### UX Improvements

- [ ] Interactive filtering
- [ ] Time-range selection
- [ ] Responsive dashboard layout

---

## 🌍 Phase 8 — Open Source & Community

### Developer Experience

- [ ] Docker & docker-compose setup
- [ ] OpenAPI / Swagger documentation
- [ ] Example environment files
- [ ] Contribution guidelines

### Project Visibility

- [ ] Public roadmap updates
- [ ] Issue templates
- [ ] Feature request guidelines

### Sustainability

- [ ] “Buy me a coffee” support
- [ ] Optional sponsor links

---

## 🧭 Long-Term Ideas

- AI-powered spending insights
- Shared goals & saving targets
- Mobile application
- Multi-currency support
- Offline-first mode

---

## 🤝 Contributing

Contributions, ideas, and feedback are welcome!
Check the issues section or open a discussion to get involved.
