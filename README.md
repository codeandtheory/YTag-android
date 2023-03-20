YCoreUI
==================

### How to generate test report
- Generating jacoco test report
  - Gradle command `clean build createMergedJacocoReport`
    - From android studio
      - Open gradle menu bar from android studio right side panel
      - Click on the gradle icon and
      - In command popup window type `clean build createMergedJacocoReport` and press enter
      - Wait for the execution completion,
      - After successful execution each module level execution report will be stored in 'module\build\reports\jacoco\html\index.html'.

### How to generate dokka report
- Gradle command single module `clean build dokkaHtml` for multi module `clean build dokkaHtmlMultiModule`
  - From android studio
  - Open gradle menu bar from android studio right side panel
  - Click on the gradle icon and
  - In command popup window type `dokkaHtml` for multi module `dokkaHtmlMultiModule`

### How to check KTLint
- Gradle command for checking lint error `ktlintCheck`
- Gradle command for formatting code `ktlintFormat`
