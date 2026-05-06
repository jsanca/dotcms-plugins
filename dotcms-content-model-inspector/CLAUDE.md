# Agent Instructions

This is a small dotCMS OSGi plugin used to expose read-only metadata for AI agents.

## Rules

- Keep the plugin read-only.
- Do not add endpoints that mutate dotCMS data.
- Do not create sites, content types, fields, pages, templates, containers, or content.
- Use dotCMS APIs through `APILocator` when needed.
- Keep DTOs simple and JSON-friendly.
- Do not use Lombok.
- Do not manually concatenate JSON.
- Keep plugin packages private/internal in the OSGi bundle.
- Run `mvn clean package` after changes.

## Current purpose

Expose:
- sites
- base content types
- existing content types and fields
- field type definitions
- layout capabilities for file-based containers/templates
- agent guidance metadata