
@RestController
@RequestMapping("/api/universidades")
public class UniversidadController {
    @Autowired
    private UniversidadRepository universidadRepository;

    @GetMapping
    public List<Universidad> getAllUniversidades() {
        return universidadRepository.findAll();
    }

    @PostMapping
    public Universidad createUniversidad(@RequestBody Universidad universidad) {
        return universidadRepository.save(universidad);
    }

    @PutMapping("/{id}")
    public Universidad updateUniversidad(@PathVariable Long id, @RequestBody Universidad universidadDetails) {
        Universidad universidad = universidadRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Universidad not found for this id :: " + id));
        universidad.setNombre(universidadDetails.getNombre());
        universidad.setDireccion(universidadDetails.getDireccion());
        return universidadRepository.save(universidad);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUniversidad(@PathVariable Long id) {
        Universidad universidad = universidadRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Universidad not found for this id :: " + id));
        universidadRepository.delete(universidad);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
