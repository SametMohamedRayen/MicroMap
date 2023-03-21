import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MicroserviceService } from '../microservice.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  constructor(private microserviceService : MicroserviceService) { }

  ngOnInit(): void {
  }
  public onAdd(addForm: NgForm): void {
    const formData = new FormData();
      formData.append("name", addForm.value.name);
      formData.append("target", addForm.value.target);
      formData.append("kongBackend", addForm.value.kongBackend);
      formData.append("topic", addForm.value.topic);
      formData.append("eventProduced", addForm.value.eventProduced);
      formData.append("api", addForm.value.api);
      formData.append("description", addForm.value.description);
      this.microserviceService.post(formData).subscribe(() => { window.location.reload() });
      
  }

}
