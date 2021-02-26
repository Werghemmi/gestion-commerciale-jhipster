import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { DetaisDevisUpdateComponent } from 'app/entities/detais-devis/detais-devis-update.component';
import { DetaisDevisService } from 'app/entities/detais-devis/detais-devis.service';
import { DetaisDevis } from 'app/shared/model/detais-devis.model';

describe('Component Tests', () => {
  describe('DetaisDevis Management Update Component', () => {
    let comp: DetaisDevisUpdateComponent;
    let fixture: ComponentFixture<DetaisDevisUpdateComponent>;
    let service: DetaisDevisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [DetaisDevisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DetaisDevisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetaisDevisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetaisDevisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetaisDevis(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetaisDevis();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
