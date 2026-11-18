"""Đóng gói source từng lab, bỏ build/cache và cấu hình Firebase cá nhân."""
from pathlib import Path
import zipfile

root = Path(__file__).resolve().parent
output = root / 'dist'
output.mkdir(exist_ok=True)
excluded = {'.gradle', '.idea', 'build', '.git'}
for project in sorted(root.glob('Lab[0-9][0-9]_*')):
    if not project.is_dir():
        continue
    destination = output / (project.name + '.zip')
    with zipfile.ZipFile(destination, 'w', zipfile.ZIP_DEFLATED) as archive:
        for file in sorted(project.rglob('*')):
            if not file.is_file():
                continue
            relative = file.relative_to(project)
            if excluded.intersection(relative.parts) or file.name in {'local.properties', 'google-services.json'} or file.suffix == '.iml':
                continue
            archive.write(file, Path(project.name) / relative)
    print(destination.name)

bundle = output / 'Android_Labs_Source.zip'
with zipfile.ZipFile(bundle, 'w', zipfile.ZIP_DEFLATED) as archive:
    for source in sorted(output.glob('Lab[0-9][0-9]_*.zip')):
        with zipfile.ZipFile(source) as lab:
            for entry in lab.infolist():
                archive.writestr(entry, lab.read(entry))
    for name in ['README.md', 'KIEM_TRA.md', 'build-all.ps1', 'package-labs.py', '.gitignore']:
        if (root / name).is_file():
            archive.write(root / name, name)
print(bundle.name)

apks = sorted((root / 'APKs').glob('Lab[0-9][0-9]_*.apk'))
if len(apks) == 10:
    apk_bundle = output / 'Android_Labs_APKs.zip'
    with zipfile.ZipFile(apk_bundle, 'w', zipfile.ZIP_DEFLATED) as archive:
        for apk in apks:
            archive.write(apk, apk.name)
    print(apk_bundle.name)
