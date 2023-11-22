# BâtisPro Equipment Rental Management

## Project Overview

BâtisPro, a company specializing in construction equipment rental, is undergoing a transformative project. Currently managing inventory through traditional paper and spreadsheet methods has led to frequent errors and rental process delays. To address this, the decision has been made to modernize the system by developing a stock management application.

The primary goal of the application is to enable BâtisPro employees to effortlessly manage inventory, track equipment availability, handle rentals, and generate detailed reports on equipment usage. Stock managers and rental agents will be the primary users, requiring an intuitive system for adding new equipment, updating existing information, recording rentals, and providing comprehensive reports.

## User Stories

- **Add New Equipment:** As a stock manager, I want to add new equipment to the inventory, specifying details such as name, available quantity, and daily rental cost.
- **Update Equipment Information:** As a stock manager, I want the ability to update information for existing equipment, including available quantity and rental cost.
- **Search for Equipment:** As a user, I want to search for specific equipment in the inventory to know its availability and rental cost.
- **Record Equipment Rentals:** As a stock manager, I want to record equipment rentals, including customer details, rental duration, and equipment specifics.
- **View Rental History:** As a user, I want to view the rental history for specific equipment.

## Additional Requirements

### Unit Tests

- **Ensure Core Functionality:** As a developer, I want to create unit tests for each class to ensure the proper functioning of core features.
- **Automatic Execution:** Unit tests should cover scenarios like adding equipment, updating information, searching for equipment, recording rentals, etc., and must be automatically executed with each project build to maintain code stability.

### Database Migration with Liquibase

- **Version-Control Database Schema:** As a developer, I want to use Liquibase to version-control database schema changes.
- **Automated Script Execution:** Each new application version must come with a Liquibase script to update the existing database, and the scripts should be automatically executed during deployment.
- **Test Before Deployment:** Migrations must be tested in a test environment before applying them to the production database to avoid undesired impacts.

## Phase 2: Comprehensive Rental Cycle Management

Building upon the success of the first phase, the second phase aims to extend the application to include features such as demand management, quotes, contracts, and file attachments.

### User Stories

#### Demand Management

- **Record Rental Requests:** As a user, I want to record a new equipment rental request, including specific details such as equipment type, estimated rental duration, and desired start date.
- **View Pending Requests:** As a stock manager, I want to view all pending requests and their statuses (pending, in progress, completed, canceled) to efficiently manage priorities.

#### Quote Management

- **Generate Quotes:** As a rental manager, I want to generate a detailed quote for an approved request, including selected equipment, estimated rental costs, and contractual conditions.
- **Customer Approval:** As a customer, I want to view and approve the quote to confirm equipment rental before contract signing.
- **Track Quotes:** As an employee, I want to track issued quotes, their status (pending, approved, rejected), and the necessary actions to convert them into contracts.

#### Contract Management

- **Generate Contracts:** As a rental manager, I want to generate a detailed contract once the quote is approved, including terms and conditions, rental dates, final costs, and details of rented equipment.
- **View Active Contracts:** As a user, I want to view my active contracts, including details of rented equipment and rental start and end dates.
- **Archive Completed Contracts:** As a manager, I want to archive completed contracts for future tracking and documentation.

#### File Management

- **Attach and Store Files:** As a user, I want to attach and store relevant files (signed contracts, invoices, equipment condition photos) to requests, quotes, and contracts for comprehensive documentation.
- **Easy Access:** As a manager, I want easy access to attached files for each request, quote, or contract for reference and auditing purposes.

## Collaboration and Contributions

We welcome collaboration and contributions from the community. To contribute, follow best development practices and open pull requests. For issues or suggestions, use the repository's issues section. Thank you for contributing to making BâtisPro Equipment Rental Management even better!
