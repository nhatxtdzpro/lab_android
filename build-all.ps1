param(
    [string]$JdkHome = $env:JAVA_HOME,
    [string[]]$Labs = @(),
    [switch]$RunTests
)
$ErrorActionPreference = 'Stop'
if (-not $JdkHome) {
    $localJdk = 'C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.5\jbr'
    if (Test-Path -LiteralPath "$localJdk\bin\javac.exe") { $JdkHome = $localJdk }
}
if (-not $JdkHome -or -not (Test-Path -LiteralPath "$JdkHome\bin\javac.exe")) {
    throw 'Truyen -JdkHome duong dan JDK 17 hoac 21 (khong dung JRE).'
}
$env:JAVA_HOME = $JdkHome
$logDir = Join-Path $PSScriptRoot 'build-logs'
$apkDir = Join-Path $PSScriptRoot 'APKs'
New-Item -ItemType Directory -Force -Path $logDir, $apkDir | Out-Null
$results = @()
$projects = Get-ChildItem -LiteralPath $PSScriptRoot -Directory | Where-Object { $_.Name -match '^Lab\d\d_' }
foreach ($project in $projects) {
    if ($Labs.Count -gt 0 -and $project.Name -notin $Labs) { continue }
    $tasks = @(':app:assembleDebug')
    if ($RunTests -and (Test-Path -LiteralPath (Join-Path $project.FullName 'app\src\test'))) { $tasks += ':app:testDebugUnitTest' }
    $log = Join-Path $logDir ($project.Name + '.log')
    Write-Output "Building $($project.Name)..."
    Push-Location -LiteralPath $project.FullName
    try {
        & .\gradlew.bat @tasks --console=plain *> $log
        $passed = $LASTEXITCODE -eq 0
    } finally { Pop-Location }
    if ($passed) {
        Copy-Item -LiteralPath (Join-Path $project.FullName 'app\build\outputs\apk\debug\app-debug.apk') -Destination (Join-Path $apkDir ($project.Name + '.apk'))
    } else { Get-Content -LiteralPath $log -Tail 35 | Write-Output }
    $results += [PSCustomObject]@{ Project = $project.Name; Build = $(if ($passed) {'PASS'} else {'FAIL'}); Log = $log }
    $results | Export-Csv -LiteralPath (Join-Path $logDir 'results.csv') -NoTypeInformation -Encoding UTF8
    Write-Output "$($project.Name): $($results[-1].Build)"
}
$results | Format-Table -AutoSize
if ($results.Build -contains 'FAIL') { exit 1 }
