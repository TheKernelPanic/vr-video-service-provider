# VR Videos Service Provider 

___

### Docker environment

```bash 
docker-compose -p vr_videos_service_provider up -d 
```

### Build __jar__ (Windows)
```bash
docker run -it --rm -v "%cd%":/usr/src/workdir -v "%cd%/target:/usr/src/workdir/target" -w /usr/src/workdir maven mvn package
```
