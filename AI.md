# AI Usage Log

I've used the IDE (vscode) built-in Copilot, specifically Claude Sonnet 4, to help improve the code quality of the app.

## Code Quality Improvements - September 17, 2025

**User Request:** "Can you help to improve the quality of the current codebase? Please also add a summary of what you've done and the prompt to the `AI.md` file."

### Summary of Improvements Made

#### 1. Enhanced Exception Handling
- **Parser.java**: Added proper `NumberFormatException` handling in `parseInts()` method to prevent crashes from invalid numeric input
- **Storage.java**: Improved error handling with proper exception propagation instead of silencing errors
- **Bob.java**: Enhanced constructor error handling to gracefully handle storage initialization failures

#### 2. Resource Management Improvements
- **Storage.java**: Replaced manual resource management with try-with-resources pattern for both `FileWriter` and `Scanner`
- Improved file I/O operations to ensure proper resource cleanup

#### 3. Encapsulation and Data Hiding
- **Task.java**: Changed `isDone` field from `protected` to `private` and added proper getter method
- **TaskList.java**: Updated to use the new `isDone()` getter method instead of direct field access

#### 4. Code Organization and Constants
- **Ui.java**: Extracted magic strings and numbers into proper constants:
  - `LINE_SEPARATOR` for the line separator pattern
  - `ERROR_PREFIX` for consistent error message formatting  
  - `ANSI_RED` and `ANSI_RESET` for console color codes
- **Todo.java**: Added constants for task type symbol and command keyword

#### 5. Documentation Improvements  
- Enhanced JavaDoc comments across multiple classes with proper parameter descriptions and return value documentation
- Added missing `@param` and `@return` annotations where needed
- Improved method descriptions for better code maintainability

#### 6. Error Message Consistency
- **Storage.java**: Replaced direct `System.out.println()` calls with proper exception throwing for consistent error handling
- **TaskList.java**: Added null safety check for storage operations

#### 7. Input Validation Enhancements
- **Parser.java**: Improved parsing logic with better error messages and empty string filtering
- **Storage.java**: Enhanced file parsing with proper empty line handling

### Technical Details

**Files Modified:**
- `src/main/java/bob/parser/Parser.java` - Exception handling improvements
- `src/main/java/bob/task/Task.java` - Encapsulation improvements  
- `src/main/java/bob/task/TaskList.java` - Method calls and null safety
- `src/main/java/bob/storage/Storage.java` - Resource management and error handling
- `src/main/java/bob/Bob.java` - Constructor error handling
- `src/main/java/bob/ui/Ui.java` - Constants and documentation
- `src/main/java/bob/task/Todo.java` - Constants and documentation
- `AI.md` - This documentation

**Key Benefits:**
- More robust error handling prevents crashes from invalid input
- Better resource management prevents memory leaks
- Improved encapsulation follows Java best practices
- Enhanced maintainability through better documentation
- Consistent error reporting throughout the application

## Task Implementation Consistency Improvements - September 17, 2025

**User Request:** "Can you make the changes you had done in Todo to other implementation of the `Task` abstract class? Please also add a summary of what you've done and the prompt to the `AI.md` file."

### Summary of Additional Improvements Made

#### 1. Consistent Constants Implementation
Applied the same constant extraction pattern from `Todo.java` to both `Deadline.java` and `Event.java`:

- **Deadline.java**: Added constants:
  - `TASK_TYPE_SYMBOL = "[D]"` for consistent display formatting
  - `COMMAND_KEYWORD = "deadline"` for parsing operations
  - `TIME_SEPARATOR = "/by"` for command syntax

- **Event.java**: Added constants:
  - `TASK_TYPE_SYMBOL = "[E]"` for consistent display formatting  
  - `COMMAND_KEYWORD = "event"` for parsing operations
  - `START_SEPARATOR = "/from"` for start time parsing
  - `END_SEPARATOR = "/to"` for end time parsing

#### 2. Enhanced Documentation Consistency
- **Deadline.java**: Improved JavaDoc comments with proper `@param` and `@return` annotations
- **Event.java**: Enhanced constructor and method documentation for consistency with `Todo.java`
- Standardized method descriptions across all Task implementations

#### 3. Code Maintainability Improvements
- Eliminated magic strings by using constants in all string operations
- Made parsing logic more maintainable by centralizing command keywords
- Improved consistency in toString() and toInputString() methods

### Technical Details

**Files Modified in This Session:**
- `src/main/java/bob/task/Deadline.java` - Constants and documentation improvements
- `src/main/java/bob/task/Event.java` - Constants and documentation improvements  
- `AI.md` - Updated documentation

**Consistency Achieved:**
- All Task subclasses (`Todo`, `Deadline`, `Event`) now follow the same pattern for constants
- Unified JavaDoc documentation style across all implementations
- Consistent use of constants instead of magic strings
- Improved code readability and maintainability

**Key Benefits:**
- **Maintainability**: Changes to command keywords or symbols only need to be made in one place
- **Consistency**: All task types follow the same coding patterns and documentation style
- **Readability**: Constants make the code self-documenting and easier to understand
- **Extensibility**: Future task types can follow the same established pattern

## Command Classes Quality Improvements - September 17, 2025

**User Request:** "Are there any improvements to the commands?"

### Summary of Command Improvements Made

#### 1. Constants for Magic Strings and Messages
Applied consistent constant extraction across all command classes to eliminate magic strings:

- **MarkCommand**: Added constants for single/multiple task prompts and error messages
- **UnmarkCommand**: Added constants following the same pattern as MarkCommand
- **DeleteCommand**: Added constants for task removal messages and count display
- **AddCommand**: Added constant for success message format
- **FindCommand**: Added constant for search result message
- **ExitCommand**: Added constant for exit message
- **InvalidCommand**: Added constant for error message format

#### 2. Enhanced Error Handling Consistency
- **Standardized Error Reporting**: Changed task validation commands to use `ui.showError()` instead of `ui.printSection()` for better error categorization
- **Consistent Error Messages**: All commands now use the same format for "Invalid task number(s)" errors
- **Better Exception Handling**: AddCommand now uses `ui.showError()` for input validation errors

#### 3. Improved Documentation
- **Complete JavaDoc**: Added comprehensive `@param` annotations for all execute() methods
- **Method Descriptions**: Enhanced descriptions to clarify parameter usage and command behavior
- **Parameter Documentation**: Clearly indicated which parameters are unused in specific commands

#### 4. Code Organization and Maintainability
- **Centralized Message Constants**: All user-facing messages are now defined as constants
- **Consistent Formatting**: All commands follow the same pattern for constants and error handling
- **Reduced Duplication**: Eliminated repeated string literals across command classes

### Technical Details

**Files Modified in This Session:**
- `src/main/java/bob/command/MarkCommand.java` - Constants and error handling improvements
- `src/main/java/bob/command/UnmarkCommand.java` - Constants and error handling improvements
- `src/main/java/bob/command/DeleteCommand.java` - Constants and error handling improvements
- `src/main/java/bob/command/AddCommand.java` - Constants and error handling improvements
- `src/main/java/bob/command/FindCommand.java` - Constants and documentation improvements
- `src/main/java/bob/command/ExitCommand.java` - Constants and documentation improvements
- `src/main/java/bob/command/ListCommand.java` - Documentation improvements
- `src/main/java/bob/command/InvalidCommand.java` - Constants and documentation improvements
- `AI.md` - Updated documentation

**Consistency Achieved:**
- All command classes now follow uniform patterns for constants and error handling
- Unified approach to error reporting using appropriate UI methods
- Comprehensive JavaDoc documentation across all command implementations
- Consistent message formatting and user experience

**Key Benefits:**
- **Maintainability**: Changes to messages require updates in only one location per class
- **User Experience**: Consistent error reporting and message formatting
- **Code Quality**: Eliminates magic strings and improves code readability
- **Documentation**: Complete parameter documentation for better code understanding
- **Error Handling**: Proper distinction between errors and informational messages
