package br.com.healthmonitor.service

import br.com.healthmonitor.model.Device
import br.com.healthmonitor.model.User
import br.com.healthmonitor.repository.DeviceRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(private val repo: DeviceRepository) {
    fun findByUser(user: User): List<Device> = repo.findAllByUser(user)
    fun save(device: Device): Device = repo.save(device)
}