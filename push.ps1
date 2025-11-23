# Push script using GitHub token for PowerShell
# Usage: .\push.ps1 YOUR_GITHUB_TOKEN

param(
    [Parameter(Mandatory=$true)]
    [string]$Token
)

$remoteUrl = "https://${Token}@github.com/fabricioguidine/ed2commarcelo.git"
Write-Host "Pushing to remote repository..."
git push $remoteUrl master

if ($LASTEXITCODE -eq 0) {
    Write-Host "Push completed successfully!"
} else {
    Write-Host "Push failed. Please check your token and try again."
}

