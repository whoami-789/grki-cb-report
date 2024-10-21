package com.grkicbreport.dto.saveContract;

import java.util.List;

public class BorrowerDTO {
   private DirectorDTO director;
   private CapitalDTO capital;
   private List<FoundersDTO> founders;

   public BorrowerDTO(DirectorDTO director, CapitalDTO capital, List<FoundersDTO> founders) {
      this.director = director;
      this.capital = capital;
      this.founders = founders;
   }

   public DirectorDTO getDirector() {
      return this.director;
   }

   public CapitalDTO getCapital() {
      return this.capital;
   }

   public List<FoundersDTO> getFounders() {
      return this.founders;
   }

   public void setDirector(DirectorDTO director) {
      this.director = director;
   }

   public void setCapital(CapitalDTO capital) {
      this.capital = capital;
   }

   public void setFounders(List<FoundersDTO> founders) {
      this.founders = founders;
   }

   public boolean equals(final Object o) {
      if (o == this) return true;
      if (!(o instanceof BorrowerDTO)) return false;
      final BorrowerDTO other = (BorrowerDTO) o;
      if (!other.canEqual((Object) this)) return false;
      final Object this$director = this.getDirector();
      final Object other$director = other.getDirector();
      if (this$director == null ? other$director != null : !this$director.equals(other$director)) return false;
      final Object this$capital = this.getCapital();
      final Object other$capital = other.getCapital();
      if (this$capital == null ? other$capital != null : !this$capital.equals(other$capital)) return false;
      final Object this$founders = this.getFounders();
      final Object other$founders = other.getFounders();
      if (this$founders == null ? other$founders != null : !this$founders.equals(other$founders)) return false;
      return true;
   }

   protected boolean canEqual(final Object other) {
      return other instanceof BorrowerDTO;
   }

   public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final Object $director = this.getDirector();
      result = result * PRIME + ($director == null ? 43 : $director.hashCode());
      final Object $capital = this.getCapital();
      result = result * PRIME + ($capital == null ? 43 : $capital.hashCode());
      final Object $founders = this.getFounders();
      result = result * PRIME + ($founders == null ? 43 : $founders.hashCode());
      return result;
   }

   public String toString() {
      return "BorrowerDTO(director=" + this.getDirector() + ", capital=" + this.getCapital() + ", founders=" + this.getFounders() + ")";
   }
}
