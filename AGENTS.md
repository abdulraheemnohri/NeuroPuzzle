# Development Guidelines for NeuroPuzzle

## Architecture
- **Offline First**: All logic must reside locally. No external API calls.
- **Data Privacy**: Store player data only in Room DB.
- **Intelligence**: Use `MutationEngine` and `PlayerAnalyzer` to drive difficulty.

## Coding Style
- Use Jetpack Compose for all UI.
- Use Room for local storage.
- Follow Clean Architecture: UI -> Engine -> Data.

## Verification
- Run `verify_syntax.sh` after changes.
- Ensure `GameManager` correctly triggers `MutationEngine`.
